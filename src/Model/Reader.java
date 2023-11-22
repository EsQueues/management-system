package Model;

import NotificationSystem.Strategy.NotificationType;
import NotificationSystem.Observer.UserObserver;

public class Reader  implements UserObserver {
    private int id;
    private  String name;
    private  String email;
    private String password;

    public Reader(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Reader(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Reader(String email, String password){
        this.email=email;
        this.password=password;
    }
    public int getId() {
        return id;
    }


    @Override
    public void handleEvent(String message, NotificationType type) {
        System.out.println(message+"\nMessage for "+getName());
    }

    public void setId(int generatedId) {
        this.id = generatedId;
    }
}
