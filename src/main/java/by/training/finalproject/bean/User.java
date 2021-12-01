package by.training.finalproject.bean;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "users", schema = "ads_db", catalog = "")
public class User extends Entity {

    //    @Id
    @Column(name = "email", nullable = false, length = 254, unique = true)
    private String login;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "name", nullable = false, length = 23)
    private String name;

    @Column(name = "phone", nullable = false, length = 13)
    private String phoneNumber;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "city_id_fk"))
    private City city;

//    @OneToOne(mappedBy = "userMessengers", orphanRemoval = true, cascade = {
//            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE
//    }, fetch = FetchType.EAGER)
    @Transient
    private Messengers messengers;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "likes",
//            joinColumns = @JoinColumn(name = "ad_info_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<AdInfo> adInfoList;

    public User() {
    }

    public User(Integer id) {
        super(id);
    }

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    public User(Integer id, String name, String phoneNumber, City city) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public User(Integer id, String name, String phoneNumber, City city, Messengers messengers) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.messengers = messengers;
    }

    public User(Integer id, String login, String password, String name, String phoneNumber, UserRole role) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(String login, String password, String name, String phoneNumber, UserRole role, Integer city, Messengers messengers) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.city = new City(city);
        this.messengers = messengers;
    }

    public User(Integer id, String login, String password, String name, String phoneNumber, UserRole role, City city) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.city = city;
    }

    public User(String login, String password, String name, String phoneNumber, Integer cityId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = new City(cityId);
    }

    public User(Integer id, String login, String password, String name, String phoneNumber, UserRole role, City city, Messengers messengers) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.city = city;
        this.messengers = messengers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Messengers getMessengers() {
        return messengers;
    }

    public void setMessengers(Messengers messengers) {
        this.messengers = messengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && role == user.role && Objects.equals(city, user.city) && Objects.equals(messengers, user.messengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, name, phoneNumber, role, city, messengers);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", city=" + city +
                ", messengers=" + messengers +
                '}';
    }
}
