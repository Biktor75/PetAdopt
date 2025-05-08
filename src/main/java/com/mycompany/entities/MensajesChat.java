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
@Table(name = "mensajes_chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensajesChat.findAll", query = "SELECT m FROM MensajesChat m"),
    @NamedQuery(name = "MensajesChat.findById", query = "SELECT m FROM MensajesChat m WHERE m.id = :id"),
    @NamedQuery(name = "MensajesChat.findByMensaje", query = "SELECT m FROM MensajesChat m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "MensajesChat.findByFecha", query = "SELECT m FROM MensajesChat m WHERE m.fecha = :fecha")})
public class MensajesChat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "destinatario_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Users destinatarioEmail;
    @JoinColumn(name = "remitente_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Users remitenteEmail;

    public MensajesChat() {
    }

    public MensajesChat(Integer id) {
        this.id = id;
    }

    public MensajesChat(Integer id, String mensaje, Date fecha) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Users getDestinatarioEmail() {
        return destinatarioEmail;
    }

    public void setDestinatarioEmail(Users destinatarioEmail) {
        this.destinatarioEmail = destinatarioEmail;
    }

    public Users getRemitenteEmail() {
        return remitenteEmail;
    }

    public void setRemitenteEmail(Users remitenteEmail) {
        this.remitenteEmail = remitenteEmail;
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
        if (!(object instanceof MensajesChat)) {
            return false;
        }
        MensajesChat other = (MensajesChat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entities.MensajesChat[ id=" + id + " ]";
    }
    
}
