package com.fiap.sprint1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Tag {

    @Id
    @Column(name = "id_tag")
    private String id;
    @Column(name = "forca_sinal")
    private Long sinal;
}
