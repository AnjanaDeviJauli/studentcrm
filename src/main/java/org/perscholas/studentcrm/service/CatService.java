package org.perscholas.studentcrm.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.CatRepoI;
import org.perscholas.studentcrm.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class CatService {

    CatRepoI catRepoI;

    @Autowired
    public CatService(CatRepoI catRepoI) {
        this.catRepoI = catRepoI;
    }


    public void catSave(Cat c){

        if(catRepoI.findByFact(c.getFact()).isPresent()){
            log.debug("cat fact exist in db" + c.getFact());
        } else {
            catRepoI.save(c);
            log.debug("cat saved" + c.getFact());
        }


    }

}
