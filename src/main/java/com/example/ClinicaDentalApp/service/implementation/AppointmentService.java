package com.example.ClinicaDentalApp.service.implementation;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.entities.Appointment;
import com.example.ClinicaDentalApp.repository.IAppointmentRepository;
import com.example.ClinicaDentalApp.service.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        appointment.setDate(LocalDate.parse(appointment.getDate().format(formatter)));
         */

        appointmentDTO.setDate(LocalDate.now());
        Appointment newAppointment = mapEntity(appointmentDTO);
        iAppointmentRepository.save(newAppointment);
        return mapDTO(newAppointment);
    }

    @Override
    public List<AppointmentDTO> findAppointmentsForPatientById(Integer patient_id) {
        List<Appointment> appointmentList =  iAppointmentRepository.findAppointmentsForPatientById(patient_id);
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return appointmentDTOList;
    }

    @Override
    public List<AppointmentDTO> findAppointmentsForDentistById(Integer patient_id) {
        List<Appointment> appointmentList =  iAppointmentRepository.findAppointmentsForDentistById(patient_id);
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return appointmentDTOList;
    }


    @Override
    public AppointmentDTO getById(Integer id) {
        Appointment appointment = iAppointmentRepository.getById(id);
        AppointmentDTO newAppointmentDTO = mapDTO(appointment);
        return newAppointmentDTO;
    }

    @Override
    public void delete(Integer id) {
        if(iAppointmentRepository.findById(id).isPresent()){
            iAppointmentRepository.getById(id).setPatient(null);
            iAppointmentRepository.getById(id).setDentist(null);
            iAppointmentRepository.deleteById(id);
        }
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Appointment newAppointment = mapEntity(appointmentDTO);
        iAppointmentRepository.save(newAppointment);
        return mapDTO(newAppointment);
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = iAppointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return  appointmentDTOList;
    }

    //-------MAPPER-----//

    private AppointmentDTO mapDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    private Appointment mapEntity(AppointmentDTO appointmentDTO){
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        return appointment;
    }
}
