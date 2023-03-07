package org.perscholas.studentcrm.secuirty;

import org.perscholas.studentcrm.data.AuthGroupRepoI;
import org.perscholas.studentcrm.data.MyUserRepoI;
import org.perscholas.studentcrm.model.AuthGroup;
import org.perscholas.studentcrm.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service

public class MyUserDetailsService implements UserDetailsService {


    MyUserRepoI myUserRepoI;
    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public MyUserDetailsService(MyUserRepoI myUserRepoI, AuthGroupRepoI authGroupRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserPrincipal
                (myUserRepoI.findByEmailAllIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Did not find the email" + username))
                        , authGroupRepoI.findByEmail(username));
    }
}
