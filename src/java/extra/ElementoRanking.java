/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

/**
 *
 * @author Magali
 */
public class ElementoRanking
{
    private String usuario;
    private int mensajesEnviados;

    public ElementoRanking(String usuario, int mensajesEnviados)
    {
        this.usuario = usuario;
        this.mensajesEnviados = 0;
    }

    @Override
    public String toString()
    {
        return "usuario=" + usuario + ", mensajesEnviados=" + mensajesEnviados;
    }

    public ElementoRanking()
    {
        this.usuario = "";
        this.mensajesEnviados = 0;
    }

    
    
    
    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public int getMensajesEnviados()
    {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(int mensajesEnviados)
    {
        this.mensajesEnviados = mensajesEnviados;
    }
    
    
}
