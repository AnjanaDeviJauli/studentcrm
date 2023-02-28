package org.perscholas.studentcrm.data;

import org.perscholas.studentcrm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepoI extends JpaRepository<Address,Integer> {


}
