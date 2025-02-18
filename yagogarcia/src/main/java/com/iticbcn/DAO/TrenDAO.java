package com.iticbcn.DAO;

import com.iticbcn.model.Tren;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class TrenDAO {
    public static void añadirTren(Tren tren, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                session.persist(tren);
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

    public static Tren consultarTren(int id, SessionFactory sessionFactory) {
        Tren train = null;

        try (Session session = sessionFactory.openSession()) {
            train = session.find(Tren.class, id);
            System.out.println(train.toString());
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        if (train != null) {

        } else {
            System.out.println("No existe ese tren");
        }
        return train;
    }

     public static List<Tren> consultarTrenes(SessionFactory sessionFactory) {
        List<Tren> trenes = null;

    	try (Session session = sessionFactory.openSession()) {

        	        Query<Tren> q = session.createQuery("from Tren",Tren.class);

        	        trenes = q.list();
    
    	}catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        if(trenes!=null && trenes.size()>0){
            for(Tren t: trenes){
                System.out.println(t);
            }
        }else{
            System.out.println("No hay trenes");
        }

        return trenes;
    }

    public static void eliminarTren(int id, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                Tren c = consultarTren(id, sessionFactoy);
                if (c != null) {
                    session.remove(c);
                    session.getTransaction().commit();
                    System.out.println("Tren con id: " + id + " ha sido eliminada correctamente");
                } else {
                    System.out.println("No se encontró ninguna tren con ID " + id);
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

    public static void editarTren(int id, String nuevoNombre, SessionFactory sessionFactory, int capacidad) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                Tren tren = consultarTren(id, sessionFactory);
                if (tren != null) {
                    tren.setNameTrain(nuevoNombre);
                    tren.setCapacity(capacidad);
                    session.merge(tren);
                    session.getTransaction().commit();
                    System.out.println("Tren con ID " + id + " actualizada con nuevo nombre: " + nuevoNombre);
                } else {
                    System.out.println("No se encontró ninguna tren con ID " + id);
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

    public static long contarTrenes(SessionFactory sessionFactory) {
        long count = 0;

        try (Session session = sessionFactory.openSession()) {
            
            Query<Long> q = session.createQuery("select count(c) from Tren c", Long.class);  
            count = q.uniqueResult(); 

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        String entidad = count!=1 ? "trenes" : "tren";
        System.out.println("Hay un total de "+count+ " "+entidad);
        return count;
    }
}
