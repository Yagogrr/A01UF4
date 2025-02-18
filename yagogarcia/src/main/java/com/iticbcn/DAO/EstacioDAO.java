package com.iticbcn.DAO;
import com.iticbcn.model.*;
import org.hibernate.SessionFactory;

public class EstacioDAO extends GenDAOImpl<Estacio> {
    public EstacioDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Estacio.class);
    }
}