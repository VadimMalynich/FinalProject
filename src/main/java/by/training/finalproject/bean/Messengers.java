package by.training.finalproject.bean;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "messengers", schema = "ads_db", catalog = "")
public class Messengers implements Serializable {

//    @Id
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
//    private User userMessengers;

    @Id
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "telegram", nullable = false)
    private Boolean telegram;

    @Column(name = "viber", nullable = false)
    private Boolean viber;

    @Column(name = "whatsapp", nullable = false)
    private Boolean whatsapp;

    public Messengers() {
        this.telegram = false;
        this.viber = false;
        this.whatsapp = false;
    }

    public Messengers(Integer id, Boolean telegram, Boolean viber, Boolean whatsapp) {
        this.id = id;
        this.telegram = telegram;
        this.viber = viber;
        this.whatsapp = whatsapp;
    }

    public Messengers(Boolean telegram, Boolean viber, Boolean whatsapp) {
        this.telegram = telegram;
        this.viber = viber;
        this.whatsapp = whatsapp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isTelegram() {
        return telegram;
    }

    public void setTelegram(Boolean telegram) {
        this.telegram = telegram;
    }

    public Boolean isViber() {
        return viber;
    }

    public void setViber(Boolean viber) {
        this.viber = viber;
    }

    public Boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messengers that = (Messengers) o;
        return Objects.equals(telegram, that.telegram) && Objects.equals(viber, that.viber) && Objects.equals(whatsapp, that.whatsapp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telegram, viber, whatsapp);
    }

    @Override
    public String toString() {
        return "Messengers{" +
                "id=" + id +
                ", telegram=" + telegram +
                ", viber=" + viber +
                ", whatsapp=" + whatsapp +
                '}';
    }
}
