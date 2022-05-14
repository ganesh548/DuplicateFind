package org.model;

import java.util.Date;

public class Profile {

    private String firstName;
    private String lastName;
    private Integer classYear;
    private String email;
    private Date date;

    public Profile(){

    }

    public Profile(String firstName, String lastName, Integer classYear, String email, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classYear = classYear;
        this.email = email;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getClassYear() {
        return classYear;
    }

    public void setClassYear(Integer classYear) {
        this.classYear = classYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", classYear=" + classYear +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
