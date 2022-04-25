package com.example.ClinicaDentalApp.service.implementation;

import com.example.ClinicaDentalApp.dto.AddressDTO;
import com.example.ClinicaDentalApp.entities.Address;
import com.example.ClinicaDentalApp.exceptions.ResourceNotFoundException;
import com.example.ClinicaDentalApp.repository.IAddressRepository;
import com.example.ClinicaDentalApp.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository iAddressRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address newAddress = mapEntity(addressDTO);
        iAddressRepository.save(newAddress);
        return mapDTO(newAddress);
    }

    @Override
    public AddressDTO getById(Integer id) throws ResourceNotFoundException {
        if (iAddressRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontro el turno con id: " + id);
        }
        Address address = iAddressRepository.getById(id);
        AddressDTO newAddressDTO = mapDTO(address);
        return newAddressDTO;
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (iAddressRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontro el turno con id: " + id);
        }
        iAddressRepository.deleteById(id);

    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) throws ResourceNotFoundException {
        if (iAddressRepository.findById(addressDTO.getId()).isPresent()) {
            throw new ResourceNotFoundException("No se encontro el turno con id: " + addressDTO.getId());
        }
        Address newAddress = mapEntity(addressDTO);
        iAddressRepository.save(newAddress);
        return mapDTO(newAddress);
    }

    @Override
    public List<AddressDTO> findAll() {
        List<Address> addressList = iAddressRepository.findAll();
        List<AddressDTO> addressDTOList = addressList.stream().map(address -> mapDTO(address)).collect(Collectors.toList());
        return  addressDTOList;
    }

    //-------MAPPER

    private AddressDTO mapDTO(Address address){
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    private Address mapEntity(AddressDTO addressDTO){
        Address address = modelMapper.map(addressDTO, Address.class);
        return address;
    }
}
