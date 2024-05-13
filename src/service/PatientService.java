package service;

import interfaces.PatientRepository;
import java.util.List;
import model.Patient;

public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean createPatient(Patient patient) {
        return patientRepository.create(patient);
    }

    public Patient getPatient(int id) {
        return patientRepository.read(id);
    }

    public List<Patient> getAllPatients(int id) {
        return patientRepository.readAll();
    }

    public boolean updatePatient(Patient patient) {
        return patientRepository.update(patient);
    }

    public boolean deletePatient(int id) {
        return patientRepository.delete(id);
    }
}
