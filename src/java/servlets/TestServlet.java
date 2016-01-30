/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.MensajeriaEJB;
import entidades.Mensajes;
import entidades.Usuario;
import extra.ElementoRanking;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Magali
 */
public class TestServlet extends HttpServlet
{

   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Calendar cal = Calendar.getInstance();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    @EJB MensajeriaEJB gestorEJB;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            //insertar usuario
//            Usuario nuevo = new Usuario("Gen", "Usuario generico", "passgen");
//            out.println(gestorEJB.insertarUsuario(nuevo));


            //modificar usuario
//            Usuario mod = new Usuario("Gen", "Usuario generico2", "passgen");
//            out.println(gestorEJB.modificarUsuario(mod));
            
            //modificar contraseña
//            Usuario modcon = new Usuario("Gen", "Usuario generico2", "passgen2");
//            out.println(gestorEJB.modificarPass(modcon));


            //eliminar usuario
//            Usuario del = new Usuario("Gen", "Usuario generico2", "passgen2");
//            out.println(gestorEJB.eliminarUsuario(del));

            //validar usuario
//            out.println(gestorEJB.validarUsuario("Mag", "pass"));

            //insertar mensaje
//            Mensajes nuevo = new Mensajes(4, "Me alegro", "Pepe", "Meg", dateFormat.format(cal.getTime()));
//            out.println(gestorEJB.insertarMensaje(nuevo));




            //listar todos los usuarios
            List<Usuario> listaUsuarios = gestorEJB.listarUsuarios();
            if(listaUsuarios.isEmpty())
                out.println("No hay ningun usuario");
            else
            {
                out.println("<h2> Lista de usuarios </h2>");
                for (Usuario u: listaUsuarios)
                {
                    out.println("<b> Usuario: </b>" + u.getUsuario() + "<b> Nombre: </b>"+ u.getNombre()+ "<b> Contraseña: </b>"+ u.getPassword() + "</br>");
                }
            }
            
            
            out.println("</br>");
            //obtener mensaje por Id
//            out.println(gestorEJB.obtenerMensajeId(3));
                
            
            //listar todos los mensajes
            List<Mensajes> listaMensajes = gestorEJB.listarMensajes();
            if(listaMensajes.isEmpty())
                out.println("No hay ningun mensaje");
            else
            {
                out.println("<h2> Lista de mensajes </h2>");
                for (Mensajes m: listaMensajes)
                {
                    out.println("<b> Texto: </b>" + m.getTexto() + "<b> Emisor: </b>"+ m.getEmisor() + "<b> Receptor: </b>"+ m.getReceptor() + "</br>");
                }
            }
            
            
            //lista de mensajes por usuario (true para emisor y false para receptor)
            List<Mensajes> listaMensajesUsuario = gestorEJB.obtenerMensajeUsuario("Pepe", true);
            if(listaMensajesUsuario.isEmpty())
                out.println("No hay ningun mensaje");
            else
            {
                out.println("<h3> Lista de mensajes por Usuario </h3>");
                for (Mensajes m: listaMensajesUsuario)
                {
                    out.println("<b> Texto: </b>" + m.getTexto() + "<b> Emisor: </b>"+ m.getEmisor() + "<b> Receptor: </b>"+ m.getReceptor() + "</br>");
                }
            }
            
            //obtener hora de ultimo inicio de sesion
//            out.println("</br>Ultima sesión: "+gestorEJB.obtenerHoraSesion("Pepe"));
            
//            out.println(gestorEJB.getRanking());
            
            //obtener ranking
            List<ElementoRanking> rankingUsuarios = gestorEJB.getRanking();
            if(rankingUsuarios.isEmpty())
                out.println("No hay ningun mensaje");
            else
            {
                out.println("<h3> Lista de mensajes por Usuario </h3>");
                for (ElementoRanking m: rankingUsuarios)
                {
                    out.println("<b> Usuario: </b>" + m.getUsuario() + "<b> Mensajes Enviados: </b>"+ m.getMensajesEnviados() + "</br>");
                }
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
