package com.ust.Security2x.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private Long id;
    private String psnumber;
    private String project;
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectstatus;
}
