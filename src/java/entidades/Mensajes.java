/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Magali
 */
@Entity
@Table(name = "mensajes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajes.findAll", query = "SELECT m FROM Mensajes m"),
    @NamedQuery(name = "Mensajes.findByIdMensaje", query = "SELECT m FROM Mensajes m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "Mensajes.findByTexto", query = "SELECT m FROM Mensajes m WHERE m.texto = :texto"),
    @NamedQuery(name = "Mensajes.findByEmisor", query = "SELECT m FROM Mensajes m WHERE m.emisor = :emisor"),
    @NamedQuery(name = "Mensajes.findByReceptor", query = "SELECT m FROM Mensajes m WHERE m.receptor = :receptor"),
    @NamedQuery(name = "Mensajes.findByFecha", query = "SELECT m FROM Mensajes m WHERE m.fecha = :fecha")})
public class Mensajes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMensaje")
    private Integer idMensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "emisor")
    private String emisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "receptor")
    private String receptor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fecha")
    private String fecha;

    public Mensajes()
    {
    }

    public Mensajes(Integer idMensaje)
    {
        this.idMensaje = idMensaje;
    }

    public Mensajes(Integer idMensaje, String texto, String emisor, String receptor, String fecha)
    {
        this.idMensaje = idMensaje;
        this.texto = texto;
        this.emisor = emisor;
        this.receptor = receptor;
        this.fecha = fecha;
    }

    public Integer getIdMensaje()
    {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje)
    {
        this.idMensaje = idMensaje;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public String getEmisor()
    {
        return emisor;
    }

    public void setEmisor(String emisor)
    {
        this.emisor = emisor;
    }

    public String getReceptor()
    {
        return receptor;
    }

    public void setReceptor(String receptor)
    {
        this.receptor = receptor;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajes)) {
            return false;
        }
        Mensajes other = (Mensajes) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Mensajes{" + "idMensaje=" + idMensaje + ", texto=" + texto + ", emisor=" + emisor + ", receptor=" + receptor + ", fecha=" + fecha + '}';
    }

    
    
}
