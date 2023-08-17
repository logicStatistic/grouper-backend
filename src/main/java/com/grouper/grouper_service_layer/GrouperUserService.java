package com.grouper.grouper_service_layer;

import com.grouper.grouper_exception_control.EmailAlreadyTakenException;
import com.grouper.grouper_exception_control.UserDoesNotExistException;
import com.grouper.grouper_model.GrouperRegistrationObject;
import com.grouper.grouper_model.GrouperRole;
import com.grouper.grouper_model.GrouperUser;
import com.grouper.grouper_repository.GrouperRoleRepository;
import com.grouper.grouper_repository.GrouperUserRepository;
import com.grouper.grouper_utility_control.GrouperUtility;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class GrouperUserService {

    @Autowired
    private final GrouperUserRepository userRepository;

    @Autowired
    private final GrouperRoleRepository roleRepository;

    public GrouperUser registerNewUser(GrouperRegistrationObject regObject){

        GrouperUser users = new GrouperUser();

        users.setFirstName(regObject.getFirstName());
        users.setLastName(regObject.getLastName());
        users.setEmail(regObject.getEmail());
        users.setDateOfBirth(regObject.getDob());

        String name = users.getFirstName() + users.getLastName();

        boolean nameTaken = true;
        String tempName = "";

        while(nameTaken){
            tempName = GrouperUtility.generateUsername(name);

            if(userRepository.findByUsername(users.getUsername()).isEmpty()) {
                nameTaken = false;
            }
        }
        users.setUsername(name);

        Set<GrouperRole> grouperRoles =users.getAuthorities();
        grouperRoles.add(roleRepository.findByAuthority("USER").get());
        users.setAuthorities(grouperRoles);

        try {
            return userRepository.save(users);
        }catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }
    public GrouperUser findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserDoesNotExistException::new);
    }
    public GrouperUser updateUser(GrouperUser users) {
        try {
            return userRepository.save(users);
        }catch (Exception exception){
            throw new EmailAlreadyTakenException();
        }
    }
    public void generateVerificationCode(String username) {
        GrouperUser users = userRepository.findByUsername(username)
                .orElseThrow(UserDoesNotExistException::new);

        users.setVerification(GrouperUtility.generateCode());
         userRepository.save(users);
    }

}
