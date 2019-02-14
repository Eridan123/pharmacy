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

    private String phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(columnDefinition = "varchar(20) default 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private DeliveryMode delivery_mode = DeliveryMode.NORMAL;

    @Column(columnDefinition = "varchar(20) default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus delivery_status = DeliveryStatus.WAITING;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment_id")
    private Payment payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryMode getDelivery_mode() {
        return delivery_mode;
    }

    public void setDelivery_mode(DeliveryMode delivery_mode) {
        this.delivery_mode = delivery_mode;
    }

    public DeliveryStatus getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(DeliveryStatus delivery_status) {
        this.delivery_status = delivery_status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
