package services.TeacherService;

import entity.Discipline;
import entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherService {
    List<Teacher> teacherList=new ArrayList<>();
    public void saveTeacher(Teacher teacher){
        teacherList.add(teacher);
    }
    public List<Teacher> getAllTeacher(){
        return teacherList;
    }
   /* public List<Teacher> getAll() {
        List<Discipline> listTeacherDiscipline=new ArrayList<>();
        List<Teacher> listTeachers=new ArrayList<>();
        List<Integer> listId=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from teacher ");
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                listTeachers.add(new Teacher(id, name, username, password, listTeacherDiscipline));
                listId.add(id);
            }
            for (Integer item: listId) {
                PreparedStatement preparedStatement1 = connection.prepareStatement("select d.id, d.name,tt.id_discipline , tt.id_teacher  from discipline d join teacher_table tt on tt.id_discipline=d.id  where id_teacher=" + item);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()) {
                    int idDiscipline1 = resultSet1.getInt(1);
                    String nameDiscipline = resultSet1.getString(2);
                    int idTeacher = resultSet1.getInt(4);
                    // PreparedStatement preparedStatement2=connection.prepareStatement("select * from discipline where id="+idDiscipline1);

                    listTeacherDiscipline.add(new Discipline(idDiscipline1, nameDiscipline));


                }
            }*/



        }

