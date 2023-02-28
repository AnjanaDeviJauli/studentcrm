package org.perscholas.studentcrm.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Address {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    String addressOne;
    @NonNull
    String addressTwo;
    @NonNull
    String city;
    @NonNull
    String state;
    @NonNull
    String zipCode;


    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Set<MyUser> myUsers = new HashSet<>();

    public void addUser(MyUser myUser){
        myUsers.add(myUser);
        myUser.getAddresses().add(this);
        log.debug("add user executed");
    }


    public void removeUser(MyUser myUser){
        myUsers.remove(myUser);
        myUser.getAddresses().remove(this);
        log.debug("remove user executed");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressOne.equals(address.addressOne) && addressTwo.equals(address.addressTwo) && city.equals(address.city) && state.equals(address.state) && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressOne, addressTwo, city, state, zipCode);
    }
}
