package Util;

import App.MyApp;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ConnectionHibernate {
    protected Transaction transaction;
    protected Session session;

    protected void init() {
        session = MyApp.sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
    }
}
