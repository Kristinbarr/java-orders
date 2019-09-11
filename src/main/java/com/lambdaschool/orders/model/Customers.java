package com.lambdaschool.orders.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long custcode;

    @Column(nullable = false)
    private String custname;

    private String custcity;
    private String workingarea;
    private String custcountry;
    private String grade;
    private double openingamt;
    private double receiveamt;
    private double paymentamt;
    private double outstandingamt;
    private String phone;
    private Long agentcode; // TODO Long foreign key (one agent to many customers) not null

    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false) // tie this cust to the agent primary key
    private Agents agents; // column is named here

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customers> orders = new ArrayList<>();

}
