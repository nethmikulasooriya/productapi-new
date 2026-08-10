package com.example.productapi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String email;
    private Integer age;
}
