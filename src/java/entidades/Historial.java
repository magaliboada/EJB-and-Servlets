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
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "Historial.findByTipoConsulta", query = "SELECT h FROM Historial h WHERE h.tipoConsulta = :tipoConsulta"),
    @NamedQuery(name = "Historial.findByUsuario", query = "SELECT h FROM Historial h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "Historial.findByFecha", query = "SELECT h FROM Historial h WHERE h.fecha = :fecha")})
public class Historial implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idHistorial")
    private Integer idHistorial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoConsulta")
    private Character tipoConsulta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fecha")
    private String fecha;

    public Historial()
    {
    }

    public Historial(Integer idHistorial)
    {
        this.idHistorial = idHistorial;
    }

    public Historial(Integer idHistorial, Character tipoConsulta, String usuario, String fecha)
    {
        this.idHistorial = idHistorial;
        this.tipoConsulta = tipoConsulta;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public Integer getIdHistorial()
    {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial)
    {
        this.idHistorial = idHistorial;
    }

    public Character getTipoConsulta()
    {
        return tipoConsulta;
    }

    public void setTipoConsulta(Character tipoConsulta)
    {
        this.tipoConsulta = tipoConsulta;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
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
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entidades.Historial[ idHistorial=" + idHistorial + " ]";
    }
    
}
