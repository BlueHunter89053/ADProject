/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adproject.Domain;

/**
 *
 * @author Albert
 */
public class Student {
    public String Name, Sname, Email;
    public int StudNum;

    public Student() {

    }

    //Setters
    public void setName() {
        this.Name = Name;
    }

    public void setSname() {
        this.Sname = Sname;
    }

    public void setEmail() {
        this.Email = Email;
    }

    public void setStudNum() {
        this.StudNum = StudNum;
    }

    //Getters
    public String getName(String Name) {
        return Name;
    }

    public String getSname(String Sname) {
        return Sname;
    }

    public String getEmail(String Email) {
        return Email;
    }

    public String getStudNum(String StudNum) {
        return StudNum;
    }
}
