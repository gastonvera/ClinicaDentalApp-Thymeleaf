package com.example.ClinicaDentalApp.controller;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.dto.DentistDTO;
import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Appointment;
import com.example.ClinicaDentalApp.service.implementation.AppointmentService;
import com.example.ClinicaDentalApp.service.implementation.DentistService;
import com.example.ClinicaDentalApp.service.implementation.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private PatientService patientService;

    /** Aqui voy a cargar los turnos*/

    /*
    //con rest
    @PostMapping(name = "save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> create(@RequestBody Appointment newAppointment) throws ServerException {
        AppointmentDTO appointmentDTO = appointmentService.save(newAppointment);
        if (appointmentDTO == null) {
            throw new ServerException("Error al insertar el turno");
        } else {
            return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
        }
    }

     */
    @PostMapping("/save")
    public String create(Appointment newAppointment){
        appointmentService.save(newAppointment);
        return "redirect:/appointments";
    }

    /** Aqui voy a buscar un turno por id*/

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findById(@PathVariable Integer id) throws ServerException{
        if(appointmentService.getById(id) == null){
            throw new ServerException("No se encontro el turno");
        } else {
            return ResponseEntity.ok(appointmentService.getById(id));
        }
    }

    /** Aqui voy a modificar a un turno*/

    /*
    @PutMapping("/update")
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) throws ServerException{
        if(appointmentService.update(appointmentDTO) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(appointmentService.update(appointmentDTO));
        }
    }

     */
    @PutMapping("/update")
    public String update(Appointment appointment){
        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("update/{id}")
    public String updateAppointment(@PathVariable Integer id, Model model) {
        List<PatientDTO> patientList = patientService.findAll();
        List<DentistDTO> dentistList = dentistService.findAll();
        Appointment appointment = appointmentService.findEntityById(id);
        model.addAttribute("dentistList", dentistList);
        model.addAttribute("patientList", patientList);
        model.addAttribute("appointment", appointment);
        return "appointmentForm";
    }
    /** Aqui voy a eliminar a un turno */

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (appointmentService.getById(id) != null) {
            appointmentService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

     */
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id){
        appointmentService.delete(id);
        return "redirect:/appointments";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> turnos() {
        return ResponseEntity.ok(appointmentService.findAllEntity());
    }

    @GetMapping
    public String findAllAppointmens(Model model) throws ServerException {
        if (appointmentService.findAll() == null){
            throw new ServerException("Lista vacia");
        } else {
            List<AppointmentDTO> appointmentList = appointmentService.findAll();
            model.addAttribute("appointmentList",appointmentList );
            return "appointments";
        }
    }

    @GetMapping("/filter/{patient_id}")
    public String filterByPatientId(@PathVariable Integer patient_id, Model model){
            List<Appointment> appointmentList = appointmentService.findAppointmentsById(patient_id);
            model.addAttribute("appointmentList",appointmentList );
            return "appointments";
    }


    /**Aqui voy a obtener los datos del dentista*/

    @GetMapping("/new")
    public String showNewForm(Model model){
        List<PatientDTO> patientList = patientService.findAll();
        List<DentistDTO> dentistList = dentistService.findAll();
        model.addAttribute("dentistList", dentistList);
        model.addAttribute("patientList", patientList);
        model.addAttribute("appointment", new Appointment());
        return "appointmentForm";
    }
}

