package com.mcit.company.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@RequiredArgsConstructor
public class MapperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String companyLoginId;
    public boolean iscompanyLoginId;

//    public CompanyProfile get() {
//    }
}
