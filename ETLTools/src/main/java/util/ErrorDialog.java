package util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * A dialog to display runtime error.
 *
 * @author Pinaki Poddar
 *
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {

    private static List<String> filters = Arrays.asList(
            "java.awt.",
            "javax.swing.",
            "sun.reflect.",
            "java.util.concurrent.");
    private static Dimension MESSAGE_SIZE = new Dimension(600, 200);
    private static Dimension STACKTRACE_SIZE = new Dimension(600, 300);
    private static Dimension TOTAL_SIZE = new Dimension(600, 500);

    static String NEWLINE = "\r\n";
    static String INDENT = "    ";

    private boolean showingDetails;
    private boolean isFiltering = true;
    private JComponent message;
    private JComponent main;
    private JScrollPane details;
    private JTextPane stacktrace;
    private final Throwable error;

    /**
     * Creates a modal dialog to display the given exception message.
     *
     * @param t the exception to display
     */
    public ErrorDialog(Throwable t) {
        this(null, t);
    }

    /**
     * Creates a modal dialog to display the given exception message.
     *
     * @param owner if non-null, then the dialog is positioned (centered) w.r.t.
     * this component
     * @param t the exception to display
     */
    public ErrorDialog(Icon icon, Throwable t) {
        super();
        setTitle("Error");
        setModal(true);
        if (icon != null && icon instanceof ImageIcon) {
            setIconImage(((ImageIcon) icon).getImage());
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        error = t;
        message = createErrorMessage(error);
        main = createContent();
        getContentPane().add(main);

        pack();
    }

    /**
     * Creates the display with the top-level exception message followed by a
     * pane (that toggles) for detailed stack traces.
     *
     * @param t a non-null exception
     */
    JComponent createContent() {
        final JButton showDetails = new JButton("Show Details >>");
        showDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showingDetails) {
                    main.remove(details);
                    main.validate();
                    main.setPreferredSize(MESSAGE_SIZE);
                } else {
                    if (details == null) {
                        details = createDetailedMessage(error);
                        StringBuilder buffer = new StringBuilder();
                        stacktrace.setText(generateStackTrace(error, buffer).toString());
                        stacktrace.setBackground(main.getBackground());
                        stacktrace.setPreferredSize(STACKTRACE_SIZE);
                    }
                    main.add(details, BorderLayout.CENTER);
                    main.validate();
                    main.setPreferredSize(TOTAL_SIZE);
                }
                showingDetails = !showingDetails;
                showDetails.setText(showingDetails ? "<< Hide Details" : "Show Details >>");
                ErrorDialog.this.pack();
            }
        });
        JPanel messagePanel = new JPanel();

        final JCheckBox filter = new JCheckBox("Filter stack traces");
        filter.setSelected(isFiltering);
        filter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isFiltering = filter.isSelected();
                StringBuilder buffer = new StringBuilder();
                stacktrace.setText(generateStackTrace(error, buffer).toString());
                stacktrace.repaint();
            }
        });
        message.setBackground(messagePanel.getBackground());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(showDetails);
        buttonPanel.add(filter);
        buttonPanel.add(Box.createHorizontalGlue());
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messagePanel.add(message, BorderLayout.CENTER);
        messagePanel.add(buttonPanel, BorderLayout.SOUTH);
        messagePanel.setPreferredSize(MESSAGE_SIZE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(messagePanel, BorderLayout.NORTH);
        return painel;
    }

    /**
     * Creates a non-editable widget to display the error message.
     *
     */
    JComponent createErrorMessage(Throwable t) {
        String txt = t.getLocalizedMessage();
        JEditorPane message = new JEditorPane();
        message.setContentType("text/plain");
        message.setEditable(false);
        message.setText(txt);
        return message;
    }

    /**
     * Creates a non-editable widget to display the detailed stack trace.
     */
    JScrollPane createDetailedMessage(Throwable t) {
        stacktrace = new JTextPane();
        stacktrace.setEditable(false);
        JScrollPane pane = new JScrollPane(stacktrace,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return pane;
    }

    /**
     * Recursively print the stack trace on the given buffer.
     */
    StringBuilder generateStackTrace(Throwable t, StringBuilder buffer) {
        buffer.append(t.getClass().getName()).append(": ").append(t.getMessage()).append(NEWLINE);
        buffer.append(toString(t.getStackTrace()));
        Throwable cause = t.getCause();
        if (cause != null && cause != t) {
            generateStackTrace(cause, buffer);
        }
        return buffer;
    }

    StringBuilder toString(StackTraceElement[] traces) {
        StringBuilder msgError = new StringBuilder();
        for (StackTraceElement e : traces) {
            if (!isFiltering || !isSuppressed(e.getClassName())) {
                String str = e.toString();
                msgError.append(INDENT).append(str).append(NEWLINE);
            }
        }
        return msgError;
    }

    /**
     * Affirms if the error messages from the given class name is to be
     * suppressed.
     */
    private boolean isSuppressed(String className) {
        for (String s : filters) {
            if (className.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String m1 = "This is test error with very very very very very long line of error message that "
                + " should not be in a single line. Another message string that shoul dbe split across word."
                + "The quick brown fox jumpled over the lazy dog";
        String m2 = "This is another test error with very long line of error message that "
                + " should not be in a single line";
        Throwable nested = new NumberFormatException(m2);
        Throwable top = new IllegalArgumentException(m1, nested);
        new ErrorDialog(top).setVisible(true);
    }

}
