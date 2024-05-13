package interfaces;

import java.util.List;
import model.Patient;

public interface PatientRepository {

    boolean create(Patient patient);

    Patient read(int id);

    List<Patient> readAll();

    boolean update(Patient patient);

    boolean delete(int id);
}
