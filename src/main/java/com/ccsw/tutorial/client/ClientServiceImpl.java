package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void save(Long id, ClientDto dto) throws Exception {
        Optional<Client> clientWithSameName = this.clientRepository.findByName(dto.getName());

        // comprobar si el nombre ya existe en otro cliente
        if (clientWithSameName.isPresent()) {
            //
            if (id == null || !clientWithSameName.get().getId().equals(id)) {
                throw new Exception("El nombre del cliente ya existe.");
            }
        }
        // mapear y guardar el cliente
        Client client;
        if (id == null) {
            client = new Client();
        } else {
            client = this.clientRepository.findById(id).orElseThrow(() -> new Exception("No existe el cliente"));
        }

        client.setName(dto.getName());
        this.clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

}
