package edu.sabanciuniv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// POJO --> Plain Old Java Object
// Entity
@Entity
public class Store {
    // variables (fields)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String storeName;
    private String storePhone;
    private double squareMeter;

    // constructors
    public Store(String storeName, String storePhone, double squareMeter) {
        this.storeName = storeName;
        this.storePhone = storePhone;
        this.squareMeter = squareMeter;
    }

    public Store() {
    }

    // custom methods
    // getters & setters
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public double getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(double squareMeter) {
        this.squareMeter = squareMeter;
    }

    // equals & hashCode & toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        return storePhone != null ? storePhone.equals(store.storePhone) : store.storePhone == null;
    }

    @Override
    public int hashCode() {
       // if(storePhone != null){
        //   return storePhone.hashCode();
       // } else {
       //     return 0;
       // }
        return storePhone != null ? storePhone.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", squareMeter=" + squareMeter +
                '}';
    }
}
