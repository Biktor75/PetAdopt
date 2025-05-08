package com.mycompany.presentacion;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    
    public boolean getLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getUserPrincipal() != null;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(email, password);  // JAAS login

            // Redirigir según el rol
            if (request.isUserInRole("admin")) {
                return "/admin/inicio.xhtml?faces-redirect=true";
            } else if (request.isUserInRole("refugio")) {
                return "/refugio/inicio.xhtml?faces-redirect=true";
            } else if (request.isUserInRole("cliente")) {
                return "/cliente/inicio.xhtml?faces-redirect=true";
            } else {
                request.logout();
                context.addMessage(null, new javax.faces.application.FacesMessage("Rol no permitido."));
                return null;
            }

        } catch (ServletException e) {
            context.addMessage(null, new javax.faces.application.FacesMessage("Error de autenticación."));
            return null;
        }
    }

    public void logout() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (ServletException e) {
            // Manejo de error
        }
    }

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

