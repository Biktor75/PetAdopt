/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "solicitudes_adopcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudesAdopcion.findAll", query = "SELECT s FROM SolicitudesAdopcion s"),
    @NamedQuery(name = "SolicitudesAdopcion.findById", query = "SELECT s FROM SolicitudesAdopcion s WHERE s.id = :id"),
    @NamedQuery(name = "SolicitudesAdopcion.findByFechaSolicitud", query = "SELECT s FROM SolicitudesAdopcion s WHERE s.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "SolicitudesAdopcion.findByEstado", query = "SELECT s FROM SolicitudesAdopcion s WHERE s.estado = :estado")})
public class SolicitudesAdopcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "cliente_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Clientes clienteEmail;
    @JoinColumn(name = "mascota_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mascotas mascotaId;

    public SolicitudesAdopcion() {
    }

    public SolicitudesAdopcion(Integer id) {
        this.id = id;
    }

    public SolicitudesAdopcion(Integer id, Date fechaSolicitud, String estado) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Clientes getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(Clientes clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public Mascotas getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Mascotas mascotaId) {
        this.mascotaId = mascotaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudesAdopcion)) {
            return false;
        }
        SolicitudesAdopcion other = (SolicitudesAdopcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.SolicitudesAdopcion[ id=" + id + " ]";
    }
    
}
