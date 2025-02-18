package com.iticbcn.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companie")
public class Companyia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompanie")
    private int idCompanie;

    @Column(name = "nameCompanie", unique = true, nullable = false)
    private String nameCompanie;

    public int getIdCompanie() {
        return idCompanie;
    }

    public void setIdCompanie(int idCompanie) {
        this.idCompanie = idCompanie;
    }

    public String getNameCompanie() {
        return nameCompanie;
    }

    public void setNameCompanie(String nameCompanie) {
        this.nameCompanie = nameCompanie;
    }

    @OneToMany(mappedBy = "companie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Tren> trains = new HashSet<Tren>();

    public Set<Tren> getTrains() {
        return trains;
    }

    public void setTrains(Set<Tren> trains) {
        this.trains = trains;
    }

    public Companyia() {
    }

    public Companyia(String nameCompanie) {
        this.nameCompanie = nameCompanie;
    }

    public Companyia(String nameCompanie, Set<Tren> trains) {
        this.nameCompanie = nameCompanie;
        this.trains = trains;
    }

    public void addTrain(Tren t) {
        if (!this.trains.contains(t)) {
            this.trains.add(t);
        }
        t.setCompanie(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Compañía:\n");
        sb.append("  ID: ").append(idCompanie).append("\n");
        sb.append("  Nombre: ").append(nameCompanie).append("\n");

        if (trains != null && !trains.isEmpty()) {
            sb.append("\n  Trenes:\n");
            for (Tren t : trains) {
                sb.append("    - ").append(t).append("\n"); 
            }
        }

        return sb.toString();
    }

}