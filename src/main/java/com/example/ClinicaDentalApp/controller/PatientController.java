package com.example.ClinicaDentalApp.controller;

import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.entities.Patient;
import com.example.ClinicaDentalApp.service.implementation.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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


    /** Aqui voy a cargar los pacientes*/

    /*
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

     */
    @PostMapping("save")
    public String create(Patient newPatient){
        patientService.save(newPatient);
        return "redirect:/patients";
    }

    /** Aqui voy a buscar un paciente por id
     * @return*/

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable int id) throws ServerException{
        if(patientService.getById(id) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.getById(id));
        }
    }

    /** Aqui voy a modificar a un paciente*/

    /*
    @PutMapping("/update")
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDTO) throws ServerException{
        if(patientService.getById(patientDTO.getId()) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.update(patientDTO));
        }
    }

     */
    @GetMapping("update/{id}")
    public String updatePatient(@PathVariable Integer id, Model model) {
        Patient patient = patientService.findEntityById(id);
        model.addAttribute("patient", patient);
        return "patientForm";
    }

    /** Aqui voy a eliminar a un paciente */

    /*
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
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Patient patient = patientService.findEntityById(id);
        if (patient != null) {
            patient.setAddress(null);
            patientService.delete(id);
        }
        return "redirect:/patients";
    }

    @GetMapping
    public String findAllPatients(Model model) throws ServerException {
        if (patientService.findAll() == null){
            throw new ServerException("Lista vacia");
        } else {
            List<PatientDTO> patientList = patientService.findAll();
            model.addAttribute("patientList", patientList);
            return "patients";
        }
    }

    /**Aqui voy a obtener los datos del dentista*/

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> turnos() {
        return ResponseEntity.ok(patientService.traerTodos());
    }

}
