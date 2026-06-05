package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import java.util.List;

public interface ClientService {

    // Recuperar todos los clientes
    List<Client> findAll();

    //Guardar sin qeu se repita el nombre
    void save(Long id, ClientDto dto) throws Exception;

    // ID para eliminar un cliente
    void delete(Long id);
}
