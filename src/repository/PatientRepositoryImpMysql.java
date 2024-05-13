package repository;

import interfaces.PatientRepository;
import java.util.List;
import model.Patient;
import utils.Constant;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.ConnectionServer;

public class PatientRepositoryImpMysql implements PatientRepository {

    @Override
    public boolean create(Patient patient) {
        Constant.QUERY = "insert into patients(name,age,description)values(?,?,?)";
        Connection conn = ConnectionServer.mysqlServerConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(Constant.QUERY, Statement.NO_GENERATED_KEYS);
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getDescription());
            Constant.MESSAGE = "Guardado con exito";
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Constant.MESSAGE = ex.getMessage();
            return false;
        }
    }

    @Override
    public Patient read(int id) {
        Constant.QUERY = "select * from patients where id=?";
        Connection conn = ConnectionServer.mysqlServerConnection();
        Patient patient = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(Constant.QUERY);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            Constant.MESSAGE = "No existe el paciente";
            if (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setDescription(rs.getString("description"));
                patient.setAge(rs.getInt("age"));
                Constant.MESSAGE = "Consultado con exito";
                return patient;
            }
            return patient;
        } catch (SQLException ex) {
            Constant.MESSAGE = ex.getMessage();
            return patient;
        }
    }

    @Override
    public List<Patient> readAll() {
        Constant.QUERY = "select * from patients order by name";
        Connection conn = ConnectionServer.mysqlServerConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Constant.URL_MYSQL);
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setDescription(rs.getString("description"));
                patient.setAge(rs.getInt("age"));
                Constant.MESSAGE = "Consultado con exito";
                patients.add(patient);
            }
        } catch (SQLException ex) {
            Constant.MESSAGE = ex.getMessage();
        }
        return patients;
    }

    @Override
    public boolean update(Patient patient) {
        Constant.QUERY = "update patients set name=?,age=?,description=? where id=?";
        Connection conn = ConnectionServer.mysqlServerConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(Constant.QUERY);
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getDescription());
            pstmt.setInt(4, patient.getId());
            Constant.MESSAGE = "Actualizado con exito";
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Constant.MESSAGE = ex.getMessage();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Constant.QUERY = "delete from patients where id=?";
        Connection conn = ConnectionServer.mysqlServerConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(Constant.QUERY);
            pstmt.setInt(1, id);
            Constant.MESSAGE = "Eliminado con exito";
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Constant.MESSAGE = ex.getMessage();
            return false;
        }
    }

}
