package com.mauricio.controladordeencomendas1.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "registered_by_id", nullable = false)
    private User registeredBy;

    @ManyToOne
    @JoinColumn(name = "received_by_id")
    private User receivedBy;

    private String sender;

    private String description;

    private LocalDateTime arrivedAt;

    private LocalDateTime receivedAt;

    @Enumerated(EnumType.STRING)
    private StatusEncomenda status;

    @Version
    private Long version;

    public Encomenda(Long id, User owner, User registeredBy, User receivedBy, String sender, String description, LocalDateTime arrivedAt, LocalDateTime receivedAt, StatusEncomenda status, Long version) {
        this.id = id;
        this.owner = owner;
        this.registeredBy = registeredBy;
        this.receivedBy = receivedBy;
        this.sender = sender;
        this.description = description;
        this.arrivedAt = arrivedAt;
        this.receivedAt = receivedAt;
        this.status = status;
        this.version = version;
    }

    public Encomenda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public User getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(User receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(LocalDateTime arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public StatusEncomenda getStatus() {
        return status;
    }

    public void setStatus(StatusEncomenda status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
