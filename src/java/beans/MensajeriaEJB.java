/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entidades.Historial;
import entidades.Mensajes;
import entidades.Usuario;
import extra.ElementoRanking;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;


/**
 *
 * @author Magali
 */
@Stateless

public class MensajeriaEJB
{
    Calendar cal = Calendar.getInstance();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @PersistenceUnit EntityManagerFactory emf;
    
    public List listarUsuarios()
    {
        return emf.createEntityManager().createNamedQuery("Usuario.findAll").getResultList();
    }
    
    public List listarMensajes()
    {
        return emf.createEntityManager().createNamedQuery("Mensajes.findAll").getResultList();
    }
    
    public String insertarUsuario(Usuario u)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.persist(u);
            em.flush();
            return "Usuario añadido";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    public String insertarMensaje(Mensajes m)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            String fecha = dateFormat.format(cal.getTime());
            String nombreusuario = m.getReceptor();
            Historial h =new Historial(4, 'C', nombreusuario, fecha);
            em.persist(h);
            em.persist(m);
            em.flush();
            return "Mensaje añadido";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    public String modificarUsuario(Usuario u)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Usuario aux = em.find(Usuario.class, u.getUsuario());
            aux.setNombre(u.getNombre());
            em.persist(aux);
            em.flush();
            return "Usuario modificado";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    public String obtenerMensajeId(int i)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Mensajes aux = em.find(Mensajes.class, i);
            em.flush();
            return aux.toString();
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    public String obtenerHoraSesion(String usuario)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query q = em.createQuery("select h.fecha from Historial h where h.usuario= :usuario and h.tipoConsulta= :tipoConsulta");
            q.setParameter("usuario", usuario);
            q.setParameter("tipoConsulta", 'I');
            List res = q.getResultList();
            Collections.sort(res);
            return (String)res.get(res.size()-1);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    
    public ArrayList<ElementoRanking> getRanking()
	{
            try
            {
                
                List<Usuario> usuarios = listarUsuarios();
                ArrayList<ElementoRanking> ranking = new ArrayList();
                ElementoRanking elemento= new ElementoRanking();
                    
                    
                    
                for(int i=0; i<usuarios.size();++i)
                {
                    List<Mensajes> mensajes = obtenerMensajeUsuario(elemento.getUsuario(), true);
                    elemento.setMensajesEnviados(0);
                    elemento.setUsuario(usuarios.get(i).getUsuario());
                    for (int x=0; x<mensajes.size();++x)
                    {
                        elemento.setMensajesEnviados(elemento.getMensajesEnviados()+1);
                    }
                    
                    ranking.add(elemento);
                }    
                ranking.add(elemento);    

    //            Collections.sort(usuarios, new CustomComparator());
    //            Collections.reverse(usuarios);
                
                return ranking;
            }
            catch(Exception ex)
            {
                return null;
            }
	}

    
    public List obtenerMensajeUsuario(String usuario, boolean emisor)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query q = null;
            if(emisor)     
            {
                q = em.createQuery("select m from Mensajes m where m.emisor= :emisor");
                q.setParameter("emisor", usuario);
            }
            else
            {
                String fecha = dateFormat.format(cal.getTime());
                //comprobar que la clave no este duplicada sino funciona
                Historial h =new Historial(5, 'R', usuario, fecha);
                em.persist(h);
                q = em.createQuery("select m from Mensajes m where m.receptor= :receptor");
                q.setParameter("receptor", usuario);
            }
            
            return q.getResultList();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
        finally { em.close(); }
    }
    
    public String modificarPass(Usuario u)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Usuario aux = em.find(Usuario.class, u.getUsuario());
            aux.setPassword(u.getPassword());
            em.persist(aux);
            em.flush();
            return "Usuario modificado";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
    
    public String validarUsuario(String usuario, String password)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Usuario aux = em.find(Usuario.class, usuario);
            em.flush();

            if(aux.getPassword().equals(password))
            {
                String fecha = dateFormat.format(cal.getTime());
                String nombreusuario = aux.getUsuario();
                Historial h =new Historial(3, 'I', nombreusuario, fecha);
                em.persist(h);
                em.flush();
                return "Hola "+aux.getNombre();
            }
            else
                return "Contraseña incorrecta.";            
        }
        catch(Exception ex)
        {
            return "Usuario incorrecto(o clave duplicada en el historial).";
        }
        finally { em.close(); }
    }
    
    public String eliminarUsuario(Usuario u)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Usuario aux = em.find(Usuario.class, u.getUsuario());
            em.remove(aux);
            em.flush();
            return "Usuario eliminado";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        finally { em.close(); }
    }
}