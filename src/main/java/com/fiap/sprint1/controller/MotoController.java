package com.fiap.sprint1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.sprint1.model.Moto;
import com.fiap.sprint1.service.MotoService;




@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    //CRUD 
    @GetMapping
    public Page<Moto> getMotos(Pageable pageable) {
        return motoService.getAllMotos(pageable);
    }

    @GetMapping("/{id}")
    public Moto getMoto(@PathVariable Long id){
        return motoService.getMotoById(id);
    }

    @PostMapping
    public Optional<Moto> postMoto(@RequestBody Moto moto) {
        return motoService.saveMoto(moto);
    }

    @PutMapping("/{id}")
    public Moto putMoto(@PathVariable Long id, @RequestBody Moto moto) {
        return motoService.updateMoto(moto);
    }

    @DeleteMapping("/{id}")
    public void deleteMoto(@PathVariable Long id) {
        motoService.deleteMoto(id);
    }

    //Busca por parâmetros (Com páginação e ordenação)


}