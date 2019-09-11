package com.lambdaschool.orders.model;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordernum;

    @Column(unique = true, nullable = false)

    private double ordamount;
    private double advanceamount;
    private Long custcode; // TODO: Long foreign key (one customer to many orders) not null
    private String orddescription;


    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false) // tie this order to the cust primary key
    private Agents agents;


}
