package org.example;

import java.time.LocalDate;

public class Piloto {
    private String code;
    private String forename;
    private String surname;
    private String dob;
    private String nationality;
    private String url;

    public Piloto(String code, String forename, String surname, String dob, String nationality, String url) {
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.nationality = nationality;
        this.url = url;
    }

    public String getCode() {
        return code;
    }



    public String getForename() {
        return forename;
    }



    public String getSurname() {
        return surname;
    }



    public String getDob() {
        return dob;
    }



    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "code='" + code + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
