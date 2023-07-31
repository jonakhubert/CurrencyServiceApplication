package com.xcode.currencyservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class CurrencyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String name;
    private LocalDateTime date;
    private double value;

    public CurrencyHistory() {}

    public CurrencyHistory(String currency, String name, LocalDateTime date, double value) {
        this.currency = currency;
        this.name = name;
        this.date = date;
        this.value = value;
    }

    // getters
    public Long getId() { return id; }
    public String getCurrency() { return currency; }
    public String getName() { return name; }
    public LocalDateTime getDate() { return date; }
    public double getValue() { return value; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setName(String name) { this.name = name; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setValue(double value) { this.value = value; }
}