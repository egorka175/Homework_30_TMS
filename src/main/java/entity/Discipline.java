package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Discipline {
    private int id;
    private String name;
    private List<Teacher> teacherList;

    public Discipline(int id, String name){
        this.id=id;
        this.name=name;
    }
}
