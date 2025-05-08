/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.presentacion;

import com.mycompany.service.RegistroService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
/**
 *
 * @author victo
 */
@Named
@RequestScoped
public class RegistroBean implements Serializable {

    private String rol; // "cliente" o "refugio"

    private String nombre, email, password, telefono, domicilio;
    private String nif, cif;
    private LocalDate fechaNacimiento;

    @Inject
    private RegistroService registroService;

    public void registrar() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        try {
            if ("cliente".equals(rol)) {
                if (fechaNacimiento == null || Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes ser mayor de edad", null));
                    return;
                }
                registroService.registrarCliente(nombre, email, password, telefono, domicilio, nif, fechaNacimiento);
            } else if ("refugio".equals(rol)) {
                registroService.registrarRefugio(nombre, email, password, telefono, domicilio, cif);
            } else {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rol no válido", null));
                return;
            }

            ctx.addMessage(null, new FacesMessage("Registro completado. Ya puedes iniciar sesión."));
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar: " + e.getMessage(), null));
        }
    }

    // Getters y setters...
}

