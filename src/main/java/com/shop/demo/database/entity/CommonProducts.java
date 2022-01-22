package com.shop.demo.database.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Common_Products", schema = "dbo", catalog = "Binance")
public class CommonProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Price")
    private Double price;
    @Basic
    @Column(name = "Quantity")
    private int quantity;

    public static List<CommonProducts> loadAll() {
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT e " +
                "FROM CommonProducts e  " );

        List<CommonProducts> list = query.getResultList();

        db.entityManager.close();

        return list;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CommonProducts loadById(int id) {
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT e " +
                "FROM CommonProducts e  " +
                "WHERE e.id = :id");

        query.setParameter("id", id);

        CommonProducts commonProducts = (CommonProducts) query.getSingleResult();

        db.entityManager.close();

        return commonProducts;
    }
}
