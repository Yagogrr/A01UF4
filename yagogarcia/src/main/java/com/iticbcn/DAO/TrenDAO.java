package com.iticbcn.DAO;

import com.iticbcn.model.*;
import org.hibernate.SessionFactory;

public class TrenDAO extends GenDAOImpl<Tren> {
    public TrenDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Tren.class);
    }
}