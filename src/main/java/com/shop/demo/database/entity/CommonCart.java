package com.shop.demo.database.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Common_Cart", schema = "dbo", catalog = "Binance")
public class CommonCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "UserId")
    private Integer userId;

    @Basic
    @Column(name = "IsValid")
    private Integer isValid;

    @Basic
    @Column(name = "Price")
    private BigDecimal price;

    @Basic
    @Column(name = "Date")
    private Date date;

    @Basic
    @Column(name = "Description")
    private String description;

    public static List<CommonCart> getHistoryOrderCart(int userId) {
        List<CommonCart> result;
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT e " +
                "FROM CommonCart e  " +
                "WHERE e.userId = :userId");

        query.setParameter("userId", userId);

        result = (List<CommonCart>) query.getResultList();
        db.entityManager.close();

        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean insert(Integer userId, BigDecimal price, String description) {
        Database db = Database.getInstace();
        try {
            db.transaction.begin();

            CommonCart commonCart = new CommonCart();
            commonCart.setUserId(userId);
            commonCart.setDate(new Date());
            commonCart.setDescription(description);
            commonCart.setIsValid(1);
            commonCart.setPrice(price);

            db.entityManager.persist(commonCart);
            db.transaction.commit();

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }finally {
            if (db.transaction.isActive()) {
                db.transaction.rollback();
            }
            db.entityManager.close();
            db.entityManagerFactory.close();
        }

        return true;
    }
}
