package com.iticbcn.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "route")
public class Trajecte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoute")
    private int id;

    @Column(name = "nameRoute", unique = true, nullable = false)
    private String name;

    @Column(name = "priceRoute", nullable = false)
    private float price;

    @Column(name = "exitHourRoute", nullable = false)
    private String exitHour;

    @Column(name = "entryHourRoute", nullable = false)
    private String entryHourRoute;

    @ManyToMany(mappedBy = "trajectes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Tren> trenes = new HashSet<>();

    public void addTren(Tren t) {
        if (!this.trenes.contains(t)) {
            trenes.add(t);
            t.addTrajecte(this);
        }
    }

    @ManyToMany(mappedBy = "trajectes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Estacio> estaciones = new HashSet<>();

    public void addEstacio(Estacio e) {
        if (!this.estaciones.contains(e)) {
            estaciones.add(e);
            e.addTrajecte(this);
        }
    }

    public Trajecte() {
    }

    public Trajecte(String name, float price, String exitHour, String entryHourRoute) {
        this.name = name;
        this.price = price;
        this.exitHour = exitHour;
        this.entryHourRoute = entryHourRoute;
    }

    public Trajecte(String name, float price, String exitHour, String entryHourRoute, Set<Tren> trenes,
            Set<Estacio> estaciones) {
        this.name = name;
        this.price = price;
        /* this.stops = stops; */
        /* this.idCompanie = idCompanie; */
        this.exitHour = exitHour;
        this.entryHourRoute = entryHourRoute;
        /* this.idTrain = idTrain; */
        this.trenes = trenes;
        this.estaciones = estaciones;
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getExitHour() {
        return exitHour;
    }

    public void setExitHour(String exitHour) {
        this.exitHour = exitHour;
    }

    public String getEntryHourRoute() {
        return entryHourRoute;
    }

    public void setEntryHourRoute(String entryHourRoute) {
        this.entryHourRoute = entryHourRoute;
    }

    public Set<Estacio> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(Set<Estacio> estaciones) {
        this.estaciones = estaciones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Trajecte:\n");
        sb.append("  ID: ").append(id).append("\n");
        sb.append("  Nombre: ").append(name).append("\n");
        sb.append("  Hora entrada: ").append(entryHourRoute).append("\n");
        sb.append("  Hora salida: ").append(exitHour).append("\n");

        if (trenes != null && !trenes.isEmpty()) {
            sb.append("\n  Trenes:\n");
            for (Tren t : trenes) {
                sb.append("    - ").append(t.getNameTrain()).append("\n");
            }
        }

        if (estaciones != null && !estaciones.isEmpty()) {
            sb.append("\n  Estaciones:\n");
            for (Estacio e : estaciones) {
                sb.append("    - ").append(e.getName()).append("\n");
            }
        }

        return sb.toString();
    }

}
