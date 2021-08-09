package services.DisciplineService;

import entity.Discipline;
import entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class DisciplineService {
    List<Discipline> disciplineList=new ArrayList<>();
    public void saveTeacher(Discipline discipline){
        disciplineList.add(discipline);
    }
    public List<Discipline> getAllDiscipline(){
        return disciplineList;
    }
}
