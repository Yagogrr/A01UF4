package com.iticbcn.DAO;

import com.iticbcn.model.*;
import org.hibernate.SessionFactory;

public class CompanyiaDAO extends GenDAOImpl<Companyia> {
    public CompanyiaDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Companyia.class);
    }
}
