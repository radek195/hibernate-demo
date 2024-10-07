package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@ToString
public class CreditCard {

    public CreditCard() {
    }

    @Id
    private int id;
    private String ownerFullName;
    private int cvv;
    private LocalDateTime issueDateTime;

}
