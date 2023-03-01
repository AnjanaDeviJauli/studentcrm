package org.perscholas.studentcrm.data;

import org.perscholas.studentcrm.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepoI extends JpaRepository<Cat, Integer> {

    Optional<Cat> findByFact(String fact);
}
