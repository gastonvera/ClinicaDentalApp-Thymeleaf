package com.example.ClinicaDentalApp.service.implementation;

import com.example.ClinicaDentalApp.dto.AddressDTO;
import com.example.ClinicaDentalApp.entities.Address;
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
    public AddressDTO save(Address address) {
        Address newAddress =  iAddressRepository.save(address);
        return mapDTO(newAddress);
    }

    @Override
    public AddressDTO getById(Integer id) {
        Address address = iAddressRepository.getById(id);
        AddressDTO newAddressDTO = mapDTO(address);
        return newAddressDTO;
    }

    @Override
    public void delete(Integer id) {
        if(iAddressRepository.findById(id).isPresent()){
            iAddressRepository.deleteById(id);
        }
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        Address address = mapEntity(addressDTO);
        Address newAddress =  iAddressRepository.save(address);
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
