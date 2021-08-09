package DAO.DisciplineDAO;

import DAO.TeacherDAO.TeacherDAO;
import entity.Discipline;
import entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO implements disciplineInterface {
    private Connection connection;

    public DisciplineDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Discipline discipline) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into discipline values (default, ?) ");
            preparedStatement.setString(1, discipline.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Discipline> getAll() {
        List<Discipline> listDiscipline = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<Teacher> listTeacher = new ArrayList<>();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Discipline discipline = new Discipline(id, name, listTeacher);
                PreparedStatement preparedStatement1 = connection.prepareStatement("select  t.id,t.name,t.username,t.password,tt.id_discipline,tt.id_teacher from teacher_table tt join teacher t on tt.id_teacher=t.id join discipline d on tt.id_discipline=d.id where tt.id_discipline=" + id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    int idTeacher = resultSet1.getInt(1);
                    String nameTeacher = resultSet1.getString(2);
                    String username = resultSet1.getString(3);
                    String password = resultSet1.getString(4);
                    listTeacher.add(new Teacher(idTeacher, nameTeacher, username, password));
                }
                listDiscipline.add(discipline);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listDiscipline;
    }

    @Override
    public Discipline getById(long id) {
        List<Teacher> listTeacher = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline where id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idDiscipline = resultSet.getInt(1);
            String nameDiscipline = resultSet.getString(2);
            PreparedStatement preparedStatement1 = connection.prepareStatement("select  t.id,t.name,t.username,t.password,tt.id_discipline,tt.id_teacher from teacher_table tt join teacher t on tt.id_teacher=t.id join discipline d on tt.id_discipline=d.id where tt.id_discipline=" + idDiscipline);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                int idTeacher = resultSet1.getInt(1);
                String nameTeacher = resultSet1.getString(2);
                String username = resultSet1.getString(3);
                String password = resultSet1.getString(4);
                listTeacher.add(new Teacher(idTeacher, nameTeacher, username, password));
            }
            Discipline discipline = new Discipline(idDiscipline, nameDiscipline, listTeacher);
            return discipline;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exist(String name) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from discipline where name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count != 0)
                result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from discipline where id=? ");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from teacher_table where id_discipline=? ");
            preparedStatement1.setLong(1, id);
            preparedStatement1.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public Discipline getByName(String name) {
        List<Teacher> listTeacher = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from discipline where name=?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idDiscipline = resultSet.getInt(1);
            String nameDiscipline = resultSet.getString(2);
            PreparedStatement preparedStatement1 = connection.prepareStatement("select  t.id,t.name,t.username,t.password,tt.id_discipline,tt.id_teacher from teacher_table tt join teacher t on tt.id_teacher=t.id join discipline d on tt.id_discipline=d.id where tt.id_discipline=" + idDiscipline);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                int idTeacher = resultSet1.getInt(1);
                String nameTeacher = resultSet1.getString(2);
                String username = resultSet1.getString(3);
                String password = resultSet1.getString(4);
                listTeacher.add(new Teacher(idTeacher, nameTeacher, username, password));
            }
            Discipline discipline = new Discipline(idDiscipline, nameDiscipline, listTeacher);
            return discipline;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
