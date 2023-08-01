package com.xcode.currencyservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "requests")
public class UserEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String name;
    private LocalDateTime date;
    private double value;

    public UserEntry() {}

    public UserEntry(String currency, String name, LocalDateTime date, double value) {
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

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof UserEntry))
            return false;

        UserEntry other = (UserEntry) o;

        return Double.compare(other.value, value) == 0 &&
                Objects.equals(id, other.id) &&
                Objects.equals(currency, other.currency) &&
                Objects.equals(name, other.name) &&
                Objects.equals(date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency, name, date, value);
    }
}