package com.example.ClinicaDentalApp.service.implementation;

import com.example.ClinicaDentalApp.dto.DentistDTO;
import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.repository.IDentistRepository;
import com.example.ClinicaDentalApp.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository iDentistRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public DentistDTO save(DentistDTO dentistDTO) {
        Dentist newDentist = mapEntity(dentistDTO);
        iDentistRepository.save(newDentist);
        return mapDTO(newDentist);
    }

    @Override
    public DentistDTO getById(Integer id) {
        Dentist dentist = iDentistRepository.getById(id);
        DentistDTO newDentistDTO = mapDTO(dentist);
        return newDentistDTO;
    }

    @Override
    public void delete(Integer id) {
        if(iDentistRepository.findById(id).isPresent()){
            iDentistRepository.deleteById(id);
        }
    }

    @Override
    public DentistDTO update(DentistDTO dentistDTO) {
        Dentist newDentist = mapEntity(dentistDTO);
        iDentistRepository.save(newDentist);
        return mapDTO(newDentist);
    }

    @Override
    public List<DentistDTO> findAll() {
        List<Dentist> dentistList = iDentistRepository.findAll();
        List<DentistDTO> dentistDTOList = dentistList.stream().map(dentist -> mapDTO(dentist)).collect(Collectors.toList());
        return  dentistDTOList;
    }


    //-------MAPPER-----//

    private DentistDTO mapDTO(Dentist dentist){
        DentistDTO dentistDTO = modelMapper.map(dentist, DentistDTO.class);
        return dentistDTO;
    }

    private Dentist mapEntity(DentistDTO dentistDTO){
        Dentist dentist = modelMapper.map(dentistDTO, Dentist.class);
        return dentist;
    }


}
