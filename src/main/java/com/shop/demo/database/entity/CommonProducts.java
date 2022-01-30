package com.shop.demo.database.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public CommonProducts(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public static List<CommonProducts> loadAll() {
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT e " +
                "FROM CommonProducts e  " );

        List<CommonProducts> list = query.getResultList();

        db.entityManager.close();

        return list;
    }

    public static void insert(CommonProducts commonProducts) {
        Database db = Database.getInstace();

        Session session = db.entityManager.unwrap(Session.class);
        db.transaction.begin();

        //Save the employee in database
        session.save(commonProducts);

        //Commit the transaction
        session.getTransaction().commit();
        db.entityManager.close();
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

    public Boolean delete(int id) {

        try {
            Database db = Database.getInstace();

            Session session = db.entityManager.unwrap(Session.class);
            db.transaction.begin();

            CommonProducts product = session.get(CommonProducts.class, id);
            session.delete(product);

            db.entityManager.close();

        }catch (Exception e){
            return false;
        }

        return true;
    }


}
