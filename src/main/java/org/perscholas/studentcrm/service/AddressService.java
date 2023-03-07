package org.perscholas.studentcrm.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.studentcrm.data.AddressRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.Address;
import org.perscholas.studentcrm.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class AddressService {
    MyUserRepoI myUserRepoI;
    MyUserService myUserService;
    AddressRepoI addressRepoI;


    @Autowired
    public AddressService(MyUserRepoI myUserRepoI, MyUserService myUserService, AddressRepoI addressRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.myUserService = myUserService;
        this.addressRepoI = addressRepoI;
    }

    public Set<Address> addAddressToUser(Address address, String userEmail) throws Exception {


        if (myUserRepoI.findByEmailAllIgnoreCase(userEmail).isPresent()) {
            log.debug("found user email " + userEmail);
            MyUser user = myUserRepoI.findByEmailAllIgnoreCase(userEmail).orElseThrow(Exception::new);
            if(addressRepoI.findByAddressOneAndAddressTwoAndCityAndStateAndZipCodeAllIgnoreCase(address.getAddressOne()
                    , address.getAddressTwo()
                    , address.getCity()
                    , address.getState()
                    , address.getZipCode()).isPresent()){
                address = addressRepoI.findByAddressOneAndAddressTwoAndCityAndStateAndZipCodeAllIgnoreCase(address.getAddressOne()
                        , address.getAddressTwo()
                        , address.getCity()
                        , address.getState()
                        , address.getZipCode()).get();
                log.debug("address found in the database " + address);


            }

            user.addAddress(address);
            user = myUserRepoI.saveAndFlush(user);
            return user.getAddresses();
        } else {
            throw new Exception("saving an address to the user " + userEmail + " did not go well!!!");

        }
    }
}
