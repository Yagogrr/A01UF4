package com.iticbcn.model;

import java.util.HashSet;
import java.util.Set;


public class Estacio {
    private int id;
    private String name;
    private Set<Trajecte> trajectes = new HashSet<>();

    // Constructor vacío obligatorio para Hibernate
    public Estacio() {
    }

    public Estacio(String name) {
        this.name = name;
    }

    // Métodos de negocio
    public void addTrajecte(Trajecte t) {
        if (!this.trajectes.contains(t)) {
            trajectes.add(t);
            t.addEstacio(this);
        }
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Trajecte> getTrajectes() {
        return trajectes;
    }

    public void setTrajectes(Set<Trajecte> trajectes) {
        this.trajectes = trajectes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Estación:\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Nombre: ").append(name).append("\n");
    
        if (trajectes != null && !trajectes.isEmpty()) {
            sb.append("\n  Trayectos:\n");
            for (Trajecte t : trajectes) {
                sb.append("    - ").append(t.getName()).append("\n");
            }
        }
    
        return sb.toString();
    }
}