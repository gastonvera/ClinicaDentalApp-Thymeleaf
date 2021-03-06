package com.example.ClinicaDentalApp.controller;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Patient;
import com.example.ClinicaDentalApp.exceptions.ResourceNotFoundException;
import com.example.ClinicaDentalApp.service.implementation.AppointmentService;
import com.example.ClinicaDentalApp.service.implementation.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    /** Aqui voy a crear mi metodo de exception not found */

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> notFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    /** Aqui voy a cargar los pacientes*/

    @PostMapping("save")
    public String create(PatientDTO newPatient){
        patientService.save(newPatient);
        return "redirect:/patients";
    }

    /** Aqui voy a buscar un paciente por id*/

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable int id) throws ResourceNotFoundException{
        return ResponseEntity.ok(patientService.getById(id));
    }

    /** Aqui voy a modificar a un paciente*/

    @GetMapping("update/{id}")
    public String updatePatient(@PathVariable Integer id, Model model) throws ResourceNotFoundException {
        PatientDTO patient = patientService.getById(id);
        model.addAttribute("patient", patient);
        return "patientForm";
    }

    /** Aqui voy a eliminar a un paciente */

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws ResourceNotFoundException {
        String response;
        List<AppointmentDTO> appointmentList = appointmentService.findAppointmentsForPatientById(id);
        if (appointmentList != null) {
            patientService.delete(id);
            response = "redirect:/patients";
        } else {
            response = "redirect:/error";
        }
        return response;
    }

    @GetMapping
    public String findAllPatients(Model model) throws ServerException {
        if (patientService.findAll() == null){
            throw new ServerException("Lista vacia");
        }
        List<PatientDTO> patientList = patientService.findAll();
        model.addAttribute("patientList", patientList);
        return "patients";
    }

    /**Aqui voy a obtener los datos del dentista*/

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    /* Guardo para rest*/


    /*
    @PutMapping("/update")
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDTO) throws ServerException{
        if(patientService.getById(patientDTO.getId()) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.update(patientDTO));
        }
    }

//con rest
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> create(@RequestBody Patient newPatient) throws ServerException {
        PatientDTO patientDTO = patientService.save(newPatient);
        if (patientDTO == null) {
            throw new ServerException("Error al insertar paciente");
        } else {
            return new ResponseEntity<>(patientDTO, HttpStatus.CREATED);
        }
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (patientService.getById(id) != null) {
            patientService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

     */

}
