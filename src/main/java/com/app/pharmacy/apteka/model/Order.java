package com.app.pharmacy.apteka.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="from_user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<Medicine> medicines;

    private Date date;

    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(columnDefinition = "varchar(20) default 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private DeliveryMode delivery_mode = DeliveryMode.NORMAL;

    private Double price;






















}
