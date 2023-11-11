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
@Table(name="store")
public class Store {
    @Id
    @SequenceGenerator(name = "store_sequence", sequenceName = "STORE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
    @Column(name = "id_store")
    private Long id;

    @Column(name = "name_store",nullable = false)
    private String name;

    @Column(name = "ubication_store",nullable = false)
    private String ubication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) && Objects.equals(name, store.name) && Objects.equals(ubication, store.ubication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ubication);
    }
}
