package com.app.pharmacy.apteka.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="from_user_id")
    private User user;

    @OneToMany(mappedBy="order")
    private Set<MedicineAmount> medicineAmounts;

    private Date date;

    private String phone_number;

    @Column(columnDefinition = "varchar(20) default 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private DeliveryMode delivery_mode = DeliveryMode.NORMAL;

    @Column(columnDefinition = "varchar(20) default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus delivery_status = DeliveryStatus.WAITING;

    private Double totalCost;

    private String address;


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

    public Set<MedicineAmount> getMedicineAmounts() {
        return medicineAmounts;
    }

    public void setMedicineAmounts(Set<MedicineAmount> medicineAmounts) {
        this.medicineAmounts = medicineAmounts;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
