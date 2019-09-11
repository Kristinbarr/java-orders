package com.lambdaschool.orders.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="agents")
public class Agents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // leaves id generation up to db
    private long agentcode; // for db to use, will be primary key

    @Column(unique = true, nullable = false)
    private String agentname;
    private String workingarea;
    private double commission;
    private String phone;
    private String country;

    @OneToMany(mappedBy = "agents", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customers> customers = new ArrayList<>(); // List is one part and many is arrayList


}
