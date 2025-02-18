package com.iticbcn.DAO;

import com.iticbcn.model.Companyia;
import com.iticbcn.model.Trajecte;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class TrajecteDAO {
    public static void añadirTrajecte(Trajecte trajecte, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                session.persist(trajecte);
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

    public static Trajecte consultarTrajecte(int id, SessionFactory sessionFactory) {
        Trajecte route = null;

        try (Session session = sessionFactory.openSession()) {
            route = session.find(Trajecte.class, id);
            System.out.println(route.toString());
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        if (route != null) {

        } else {
            System.out.println("No existe ese trajecte");
        }
        return route;
    }

     public static List<Trajecte> consultarTrayectos(SessionFactory sessionFactory) {
        List<Trajecte> trayectos = null;

    	try (Session session = sessionFactory.openSession()) {

        	        Query<Trajecte> q = session.createQuery("from Trajecte",Trajecte.class);

        	        trayectos = q.list();
    
    	}catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        if(trayectos!=null && trayectos.size()>0){
            for(Trajecte c: trayectos){
                System.out.println(c);
            }
        }else{
            System.out.println("No hay trayectos");
        }

        return trayectos;
    }

    public static void eliminarTrajecte(int id, SessionFactory sessionFactoy) {
        try (Session session = sessionFactoy.openSession()) {
            session.beginTransaction();
            try {
                Trajecte c = consultarTrajecte(id, sessionFactoy);
                if (c != null) {
                    session.remove(c);
                    session.getTransaction().commit();
                    System.out.println("Trajecte con id: " + id + " ha sido eliminada correctamente");
                } else {
                    System.out.println("No se encontró ninguna trajecte con ID " + id);
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

    public static void editarTrajecte(int id, String nuevoNombre, String nuevaHoraDeEntrada, String nuevaHoraDeSalida, SessionFactory sessionFactory, int precio) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                Trajecte trajecte = consultarTrajecte(id, sessionFactory);
                if (trajecte != null) {
                    trajecte.setName(nuevoNombre);
                    trajecte.setPrice(precio);
                    trajecte.setEntryHourRoute(nuevaHoraDeEntrada);
                    trajecte.setExitHour(nuevaHoraDeSalida);
                    session.merge(trajecte);
                    session.getTransaction().commit();
                    System.out.println("Trajecte con ID " + id + " actualizada con nuevo nombre: " + nuevoNombre);
                } else {
                    System.out.println("No se encontró ninguna trajecte con ID " + id);
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

    public static long contarTrayectos(SessionFactory sessionFactory) {
        long count = 0;

        try (Session session = sessionFactory.openSession()) {
            
            Query<Long> q = session.createQuery("select count(c) from Trajecte c", Long.class);  
            count = q.uniqueResult(); 

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        String entidad = count!=1 ? "trayectos" : "trayecto";
        System.out.println("Hay un total de "+count+ " "+entidad);
        return count;
    }
}
