package com.project.socialPlatform.connectionService.service;

import com.project.socialPlatform.connectionService.entity.Person;
import com.project.socialPlatform.connectionService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnectionsOfUser(Long userId){
        log.info("getting 1st degree connections of user "+ userId);

        return personRepository.getFirstDegreeConnections(userId);
    }
}
