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
@Table(name="product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id_product")
    private Long id;

    @Column(name = "name_product",nullable = false)
    private String name;

    @Column(name = "description_product",nullable = false)
    private String description;

    @Column(name = "price_product",nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(store, product.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, store);
    }
}
