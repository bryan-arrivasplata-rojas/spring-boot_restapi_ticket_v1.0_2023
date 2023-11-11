package com.ticket.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="client")
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "CLIENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id_client")
    private Long id;

    @Column(name = "name_client",nullable = false)
    private String name;

    @Column(name = "lastname_client",nullable = false)
    private String lastname;

    @Column(name = "email_client",nullable = false)
    private String email;

    @Column(name = "number_client",nullable = false)
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(lastname, client.lastname) && Objects.equals(email, client.email) && Objects.equals(number, client.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, email, number);
    }
}
