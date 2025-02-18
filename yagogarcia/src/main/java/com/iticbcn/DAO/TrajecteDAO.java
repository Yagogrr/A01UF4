package com.iticbcn.DAO;
import com.iticbcn.model.*;
import org.hibernate.SessionFactory;

public class TrajecteDAO extends GenDAOImpl<Trajecte> {
    public TrajecteDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Trajecte.class);
    }
}