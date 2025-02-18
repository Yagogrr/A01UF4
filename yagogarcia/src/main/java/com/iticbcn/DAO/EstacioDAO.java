package com.iticbcn.DAO;

import com.iticbcn.model.Estacio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class EstacioDAO {
    public static void añadirEstacio(Estacio estacio, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                session.persist(estacio);
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

    public static Estacio consultarEstacio(int id, SessionFactory sessionFactory) {
        Estacio station = null;

        try (Session session = sessionFactory.openSession()) {
            station = session.find(Estacio.class, id);
            System.out.println(station.toString());
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        if (station != null) {

        } else {
            System.out.println("No existe ese estacio");
        }
        return station;
    }

     public static List<Estacio> consultarEstaciones(SessionFactory sessionFactory) {
        List<Estacio> estaciones = null;

    	try (Session session = sessionFactory.openSession()) {

        	        Query<Estacio> q = session.createQuery("from Estacio",Estacio.class);

        	        estaciones = q.list();
    
    	}catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        if(estaciones!=null && estaciones.size()>0){
            for(Estacio c: estaciones){
                System.out.println(c);
            }
        }else{
            System.out.println("No hay estaciones");
        }

        return estaciones;
    }

    public static void eliminarEstacio(int id, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                Estacio c = consultarEstacio(id, sessionFactoy);
                if (c != null) {
                    session.remove(c);
                    session.getTransaction().commit();
                    System.out.println("Estacio con id: " + id + " ha sido eliminada correctamente");
                } else {
                    System.out.println("No se encontró ninguna estacio con ID " + id);
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

    public static void editarEstacio(int id, String nuevoNombre, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                Estacio estacio = consultarEstacio(id, sessionFactory);
                if (estacio != null) {
                    estacio.setName(nuevoNombre);
                    session.merge(estacio);
                    session.getTransaction().commit();
                    System.out.println("Estacio con ID " + id + " actualizada con nuevo nombre: " + nuevoNombre);
                } else {
                    System.out.println("No se encontró ninguna estacio con ID " + id);
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

    public static long contarEstaciones(SessionFactory sessionFactory) {
        long count = 0;

        try (Session session = sessionFactory.openSession()) {
            
            Query<Long> q = session.createQuery("select count(c) from Estacio c", Long.class);  
            count = q.uniqueResult(); 

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        String entidad = count!=1 ? "estaciones" : "estación";
        System.out.println("Hay un total de "+count+ " "+entidad);
        return count;
    }
}
