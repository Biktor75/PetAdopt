package com.mycompany.service;

import com.mycompany.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Stateless
public class RegistroService {

    @PersistenceContext
    private EntityManager em;

    public void registrarCliente(String nombre, String email, String password, String telefono,
                                 String domicilio, String nif, LocalDate fechaNacimiento) {

        if (em.find(Users.class, email) != null) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Crear usuario base
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(encriptar(password));
        user.setNombre(nombre);
        em.persist(user);

        // Añadir rol
        UserGroups group = new UserGroups();
        group.setEmail(email);
        group.setGroupname("cliente");
        em.persist(group);

        // Insertar en Clientes
        Clientes cliente = new Clientes();
        cliente.setEmail(email);
        cliente.setUsers(user);
        cliente.setApellidos(""); // opcional, puedes adaptarlo
        cliente.setDomicilio(domicilio);
        cliente.setNif(nif);
        cliente.setTelefono(telefono);
        cliente.setFechaNacimiento(fechaNacimiento);
        em.persist(cliente);
    }

    public void registrarRefugio(String nombre, String email, String password, String telefono,
                                 String domicilio, String cif) {

        if (em.find(Users.class, email) != null) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Crear usuario base
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(encriptar(password));
        user.setNombre(nombre);
        em.persist(user);

        // Añadir rol
        UserGroups group = new UserGroups();
        group.setEmail(email);
        group.setGroupname("refugio");
        em.persist(group);

        // Insertar en Refugios
        Refugios refugio = new Refugios();
        refugio.setEmail(email);
        refugio.setUsers(user);
        refugio.setCif(cif);
        refugio.setDomicilio(domicilio);
        refugio.setTelefono(telefono);
        refugio.setAutorizado(false); // Se autoriza luego por el admin
        em.persist(refugio);
    }

    private String encriptar(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar contraseña");
        }
    }
}
