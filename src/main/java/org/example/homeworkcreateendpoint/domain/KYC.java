package org.example.homeworkcreateendpoint.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Know your customer
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "kycs")
public class KYC {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false, length = 12)
    private String nationalCardId;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "cust_id")
    private Customer customer;
}