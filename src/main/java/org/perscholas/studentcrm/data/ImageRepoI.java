package org.perscholas.studentcrm.data;

import jakarta.transaction.Transactional;
import org.perscholas.studentcrm.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository @Transactional(rollbackOn = Exception.class)
public interface ImageRepoI extends JpaRepository<Image, Integer> {

    Optional<Image> findByName(String name);
    Optional<Image> findByUrl(String url);
}
