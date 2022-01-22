package com.shop.demo.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Common_Login", schema = "dbo", catalog = "Binance")
public class CommonLogin implements Serializable {

    public static final String TABLE_NAME = "Common_Login";



    public static final class Collumns{
        public static String ID                     = "Id";
        public static String LOGIN                  = "Login";
        public static String PASSWORD               = "Password";
        public static String IS_VALID               = "IsValid";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private int isValid;

    @Basic
    @Column(name = "Login", nullable = false, length = 60)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 2147483647)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IsValid", nullable = false)
    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonLogin that = (CommonLogin) o;
        return id == that.id &&
                isValid == that.isValid &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, isValid);
    }

    public boolean insertCommonLogin(String login, String password) {
        Database db = Database.getInstace();
        try {
            db.transaction.begin();

            CommonLogin commonLogin = new CommonLogin();
            commonLogin.setLogin(login);
            commonLogin.setPassword(password);
            commonLogin.setIsValid(1);

            db.entityManager.persist(commonLogin);
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

    public static boolean isThereThisLogin(String login) {
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT count(e) FROM CommonLogin e  WHERE e.login = :login");
        query.setParameter("login", login);
        Long numberRecords = (Long) query.getSingleResult();
        db.entityManager.close();

        if (numberRecords != 0)
            return true;

        return false;
    }

    public static boolean tryToLogIn(String login, String password){
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT count(e) " +
                "FROM CommonLogin e  " +
                "WHERE e.login = :login AND e.password = :password");

        query.setParameter("login", login);
        query.setParameter("password", password);
        Long numberRecords = (Long) query.getSingleResult();
        db.entityManager.close();

        if (numberRecords != 0)
            return true;

        return false;
    }

    public static CommonLogin getUserData(String login, String password){
        CommonLogin result;
        Database db = Database.getInstace();

        Query query = db.entityManager.createQuery("SELECT e " +
                "FROM CommonLogin e  " +
                "WHERE e.login = :login AND e.password = :password");

        query.setParameter("login", login);
        query.setParameter("password", password);

        result = (CommonLogin) query.getSingleResult();
        db.entityManager.close();


        return result;
    }


    public int getId() {
        return id;
    }
}
