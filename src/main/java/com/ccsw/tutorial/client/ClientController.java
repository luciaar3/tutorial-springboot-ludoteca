package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "")
    public List<ClientDto> findAll() {
        List<Client> clients = this.clientService.findAll();

        return clients.stream().map(e -> modelMapper.map(e, ClientDto.class)).collect(Collectors.toList());
    }

    @PostMapping(value = { "", "/{id}" })
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto dto) throws Exception {
        this.clientService.save(id, dto);
    }

    @RequestMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.clientService.delete(id);
    }

}
