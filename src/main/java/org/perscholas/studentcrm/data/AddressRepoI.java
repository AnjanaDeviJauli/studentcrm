package org.perscholas.studentcrm.data;

import jakarta.transaction.Transactional;
import org.perscholas.studentcrm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository@Transactional(rollbackOn = Exception.class)
public interface AddressRepoI extends JpaRepository<Address,Integer> {

    Optional<Address> findByAddressOneAndAddressTwoAndCityAndStateAndZipCodeAllIgnoreCase
            (String addressOne, String addressTwo, String city, String state, String zipCode);


}
