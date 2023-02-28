package org.perscholas.studentcrm.data;

import org.perscholas.studentcrm.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepoI extends JpaRepository<Image, Integer> {
}
