/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ResourceBundle;

/**
 *
 * @author cesar
 */
public class Language {

    public static final String fileLanguagePtBr = "Bundle_pt_BR";
    public static final String fileLanguageEnUs = "Bundle_en_US";

    private static String fileLanguage = fileLanguagePtBr;

    public static void setFileLanguage(String fileLanguage) {
        Language.fileLanguage = fileLanguage;
    }

    public static ResourceBundle getBundle() {
        return java.util.ResourceBundle.getBundle("language/" + fileLanguage);
    }

}
