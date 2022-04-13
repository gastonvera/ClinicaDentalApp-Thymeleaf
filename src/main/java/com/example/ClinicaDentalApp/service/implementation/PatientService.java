package com.example.ClinicaDentalApp.service.implementation;

import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Patient;
import com.example.ClinicaDentalApp.repository.IPatientRepository;
import com.example.ClinicaDentalApp.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


    @Service
    public class PatientService implements IPatientService {

        @Autowired
        private IPatientRepository iPatientRepository;

        @Autowired
        public ModelMapper modelMapper;

        @Override
        public PatientDTO save(Patient patient) {
            patient.setAdmissionDate(LocalDate.now());
            Patient newPatient =  iPatientRepository.save(patient);
            return mapDTO(newPatient);
        }

        @Override
        public PatientDTO findByEmail(String email) {
            return null;
        }

        @Override
        public List<Patient> traerTodos() {
            return iPatientRepository.findAll();
        }

        @Override
        public Patient findEntityById(Integer id) {
            return iPatientRepository.getById(id);
        }

        @Override
        public PatientDTO getById(Integer id) {
            Patient patient = iPatientRepository.getById(id);
            PatientDTO newPatientDTO = mapDTO(patient);
            return newPatientDTO;
        }

        @Override
        public void delete(Integer id) {
            if(iPatientRepository.findById(id).isPresent()){
                iPatientRepository.deleteById(id);
            }
        }

        @Override
        public PatientDTO update(PatientDTO patientDTO) {
            Patient patient = mapEntity(patientDTO);
            Patient newPatient = iPatientRepository.save(patient);
            return mapDTO(newPatient);
        }

        @Override
        public List<PatientDTO> findAll() {
            List<Patient> patientList = iPatientRepository.findAll();
            List<PatientDTO> patientDTOList = patientList.stream().map(patient -> mapDTO(patient)).collect(Collectors.toList());
            return  patientDTOList;
        }


        //-------MAPPER

        private PatientDTO mapDTO(Patient patient){
            PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
            return patientDTO;
        }

        private Patient mapEntity(PatientDTO patientDTO){
            Patient patient = modelMapper.map(patientDTO, Patient.class);
            return patient;
        }
}
