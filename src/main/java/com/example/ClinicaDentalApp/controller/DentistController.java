package com.example.ClinicaDentalApp.controller;

import com.example.ClinicaDentalApp.dto.DentistDTO;
import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Appointment;
import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.service.implementation.AppointmentService;
import com.example.ClinicaDentalApp.service.implementation.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @Autowired
    private AppointmentService appointmentService;

    /** Aqui voy a cargar los dentistas */

    @PostMapping("/save")
    public String create(Dentist newDentist){
        dentistService.save(newDentist);
        return "redirect:/dentists";
    }

    /** Aqui voy a buscar un dentista por id */

    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable int id) throws ServerException{
        if(dentistService.getById(id) == null){
            throw new ServerException("No se encontro el dentista");
        } else {
            return ResponseEntity.ok(dentistService.getById(id));
        }
    }

    /** Aqui voy a modificar a un dentista */

    @GetMapping("update/{id}")
    public String updateDentist(@PathVariable Integer id, Model model) {
        Dentist dentist = dentistService.findEntityById(id);
        model.addAttribute("dentist", dentist);
        return "dentistForm";
    }

    /** Aqui voy a eliminar a un odontologo */

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        List<Appointment> appointmentsList = appointmentService.findAppointmentsForDentistById(id);
        if(appointmentsList != null){
            dentistService.delete(id);
            return "redirect:/dentists";
        } else {
            return "redirect:/error";
        }

    }

    @GetMapping
    public String findAllDentists(Model model) throws ServerException {
        if (dentistService.findAll() == null){
            throw new ServerException("Lista vacia");
        } else {
            List<DentistDTO> dentistList = dentistService.findAll();
            model.addAttribute("dentistList", dentistList);
            return "dentists";
        }
    }

    /**Aqui voy a obtener los datos del dentista FORM*/

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("dentist", new Dentist());
        return "dentistForm";
    }

    /** Voy a guardar para rest*/

     /*

    //Con rest
    @PostMapping("/save")
    public ResponseEntity<DentistDTO> create(@RequestBody Dentist newDentist) throws ServerException {
        DentistDTO dentistDTO = dentistService.save(newDentist);
        if (dentistDTO == null) {
            throw new ServerException("Error al insertar dentista");
        } else {
            return new ResponseEntity<>(dentistDTO, HttpStatus.CREATED);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DentistDTO> update(@RequestBody DentistDTO dentistDTO) throws ServerException{
        if(dentistService.getById(dentistDTO.getId()) == null){
            throw new ServerException("No se encontro el odontologo");
        } else {
            return ResponseEntity.ok(dentistService.update(dentistDTO));
        }
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        if (dentistService.getById(id) != null) {
            dentistService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

     */
}
