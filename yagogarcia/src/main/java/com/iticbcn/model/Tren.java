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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;;

@Entity
@Table(name = "train")
public class Tren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrain")
    private int idTrain;

    @Column(name = "nameTrain", unique = true, nullable = false)
    private String nameTrain;

    @Column(name = "capacityTrain", nullable = false)
    private int capacity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCompanie", foreignKey = @ForeignKey(name = "FK_TRAIN_COMPANIE"), nullable = false)
    private Companyia companie;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tren_trajecte", joinColumns = {
            @JoinColumn(name = "idTrain", foreignKey = @ForeignKey(name = "FK_TT_TRAIN")) }, inverseJoinColumns = {
                    @JoinColumn(name = "idRoute", foreignKey = @ForeignKey(name = "FK_TT_ROUTE")) })
    private Set<Trajecte> trajectes = new HashSet<>();

    public void addTrajecte(Trajecte t) {
        if (!this.trajectes.contains(t)) {
            trajectes.add(t);
            t.addTren(this);
        }
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getNameTrain() {
        return nameTrain;
    }

    public void setNameTrain(String nameTrain) {
        this.nameTrain = nameTrain;
    }

    public int getCapacity() {
        return capacity;
    }

    public Tren() {
    }

    public Tren(String nameTrain, int capacity, Companyia companie) {
        this.nameTrain = nameTrain;
        this.capacity = capacity;
        this.companie = companie;
    }

    public Tren(String nameTrain, int capacity) {
        this.nameTrain = nameTrain;
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Companyia getCompanie() {
        return companie;
    }

    public void setCompanie(Companyia companie) {
        this.companie = companie;
    }

    public Set<Trajecte> getTrajectes() {
        return trajectes;
    }

    public void setTrajectes(Set<Trajecte> trajectes) {
        this.trajectes = trajectes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tren:\n");
        sb.append("  ID: ").append(idTrain).append("\n");
        sb.append("  Nombre: ").append(nameTrain).append("\n");

        if (trajectes != null && !trajectes.isEmpty()) {
            sb.append("\n  Trayectos:\n"); // Corrected: "Trayectos" not "Trenes"
            for (Trajecte t : trajectes) {
                sb.append("    - ").append(t).append("\n"); // Simplified: t.toString() is implicit
            }
        }
        return sb.toString();
    }

}
