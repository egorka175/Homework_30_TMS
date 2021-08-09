package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Teacher {
    int id;
    String name;
    String username;
    String password;
    List<Discipline>listDisipline;


    public Teacher(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Teacher(int id, String name) {
     this.id=id;
     this.name=name;
    }
}