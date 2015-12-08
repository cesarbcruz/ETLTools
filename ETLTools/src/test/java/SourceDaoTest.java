/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cesar.etltools.dao.AddressSourceDao;
import com.cesar.etltools.dao.CriadorDeSessao;
import com.cesar.etltools.dao.SourceDao;
import com.cesar.etltools.dao.TaskDao;
import com.cesar.etltools.dominio.AddressSource;
import com.cesar.etltools.dominio.Source;
import com.cesar.etltools.dominio.Task;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author cesar
 */
public class SourceDaoTest {

    public SourceDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of confirm method, of class SourceDao.
     */
    @Test
    public void testDao() {
        Session sessao = new CriadorDeSessao().getSession();
        TaskDao taskDao = new TaskDao(sessao);
        Task task = taskDao.list().get(0);
        SourceDao sourceDao = new SourceDao(sessao);
        Source source = sourceDao.byTask(task);
        AddressSourceDao addressSourceDao = new AddressSourceDao(sessao);
        AddressSource remover = null;
        for (AddressSource addressSource : source.getAddressSource()) {
            if (addressSource.getIp().equals("192.168.0.151")) {
                remover = addressSource;
            }
        }
        if (remover != null) {
            source.getAddressSource().remove(remover);
            addressSourceDao.deletar(remover);
        }
    }

}
