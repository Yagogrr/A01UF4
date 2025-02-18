package com.iticbcn.DAO;

import com.iticbcn.model.Companyia;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CompanyiaDAO {
    public static void añadirCompanyia(Companyia companyia, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                session.persist(companyia);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage());
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            }
        }

    }

    public static Companyia consultarCompanyia(int id, SessionFactory sessionFactory) {
        Companyia companie = null;

        try (Session session = sessionFactory.openSession()) {
            companie = session.find(Companyia.class, id);
            System.out.println(companie.toString());
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            if(companie==null){
                System.out.println("No existeix la companyia");
            }else{
                System.err.println("Error inesperado: " + e.getMessage());
            }
            
        }
        return companie;
    }

    public static List<Companyia> consultarCompanyias(SessionFactory sessionFactory) {
        List<Companyia> companies = null;

    	try (Session session = sessionFactory.openSession()) {

        	        Query<Companyia> q = session.createQuery("from Companyia",Companyia.class);

        	        companies = q.list();
    
    	}catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        if(companies!=null && companies.size()>0){
            for(Companyia c: companies){
                System.out.println(c);
            }
        } else{
            System.out.println("No hay compañias");
        }

        return companies;
    }

    public static void eliminarCompanyia(int id, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                Companyia c = consultarCompanyia(id, sessionFactoy);
                if (c != null) {
                    session.remove(c);
                    session.getTransaction().commit();
                    System.out.println("Companyia con id: " + id + " ha sido eliminada correctamente");
                } else {
                    System.out.println("No se encontró ninguna compañia con ID " + id);
                    session.getTransaction().rollback();
                }
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage());
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperado: " + e.getMessage());

                }

            }
        }
    }

    public static void editarCompanyia(int id, String nuevoNombre, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                Companyia companyia = consultarCompanyia(id, sessionFactory);
                if (companyia != null) {
                    companyia.setNameCompanie(nuevoNombre);
                    session.merge(companyia);
                    session.getTransaction().commit();
                    System.out.println("Compañia con ID " + id + " actualizada con nuevo nombre: " + nuevoNombre);
                } else {
                    System.out.println("No se encontró ninguna compañia con ID " + id);
                    session.getTransaction().rollback();
                }
            } catch (HibernateException e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error en Hibernate: " + e.getMessage());
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                    System.err.println("Error inesperado: " + e.getMessage());

                }
            }

        }
    }

    public static long contarCompanyias(SessionFactory sessionFactory) {
        long count = 0;

        try (Session session = sessionFactory.openSession()) {
           
            Query<Long> q = session.createQuery("select count(c) from Companyia c", Long.class); 
            count = q.uniqueResult(); 

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        String entidad = count!=1 ? "compañias" : "compañia";
        System.out.println("Hay un total de "+count+ " "+entidad);
        return count;
    }
}
