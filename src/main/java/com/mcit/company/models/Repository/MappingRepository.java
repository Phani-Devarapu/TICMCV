package com.mcit.company.models.Repository;

import com.mcit.company.models.MapperClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingRepository extends JpaRepository<MapperClass,Integer> {
    MapperClass findBycompanyLoginId(String name);

//    public MaapperClass findByUserName(String companyLoginId);
}
