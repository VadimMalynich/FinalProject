package by.training.finalproject.bean;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private String name;
    private String phoneNumber;
    private UserRole role;
    private City city;
    private Messengers messengers;

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
