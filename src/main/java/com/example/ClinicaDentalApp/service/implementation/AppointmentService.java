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
    public AppointmentDTO save(Appointment appointment) {
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        appointment.setDate(LocalDate.parse(appointment.getDate().format(formatter)));
         */
        appointment.setDate(LocalDate.now());
        Appointment newAppointment = iAppointmentRepository.save(appointment);
        return mapDTO(newAppointment);
    }

    @Override
    public List<Appointment> findAllEntity() {
        return iAppointmentRepository.findAll();
    }

    @Override
    public Appointment findEntityById(Integer id) {
        Appointment appointment = iAppointmentRepository.getById(id);
        return appointment;
    }

    @Override
    public List<Appointment> findAppointmentsById(Integer patient_id) {
        List<Appointment> appointmentList =  iAppointmentRepository.findAppointmentsById(patient_id);
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return appointmentList;
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
        Appointment appointment = mapEntity(appointmentDTO);
        Appointment newAppointment =  iAppointmentRepository.save(appointment);
        return mapDTO(newAppointment);
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointmentList = iAppointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOList = appointmentList.stream().map(appointment -> mapDTO(appointment)).collect(Collectors.toList());
        return  appointmentDTOList;
    }

    //-------MAPPER

    private AppointmentDTO mapDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = modelMapper.map(appointment, AppointmentDTO.class);
        return appointmentDTO;
    }

    private Appointment mapEntity(AppointmentDTO appointmentDTO){
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        return appointment;
    }
}
