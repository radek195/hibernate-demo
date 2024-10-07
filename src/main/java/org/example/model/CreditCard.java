package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard {

    @Id
    private int id;
    private String ownerFullName;
    private int cvv;
}
