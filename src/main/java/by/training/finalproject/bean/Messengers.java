package by.training.finalproject.bean;

import java.io.Serializable;
import java.util.Objects;

public class Messengers implements Serializable {
    private Boolean telegram;
    private Boolean viber;
    private Boolean whatsapp;

    public Messengers() {
        this.telegram = false;
        this.viber = false;
        this.whatsapp = false;
    }

    public Messengers(Boolean telegram, Boolean viber, Boolean whatsapp) {
        this.telegram = telegram;
        this.viber = viber;
        this.whatsapp = whatsapp;
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
                "telegram=" + telegram +
                ", viber=" + viber +
                ", whatsapp=" + whatsapp +
                '}';
    }
}
