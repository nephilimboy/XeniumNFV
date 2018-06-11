package com.ahghorab.xenonet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ahghorab.xenonet.domain.Server;
import com.ahghorab.xenonet.domain.User;

public interface ServerRepository  extends JpaRepository<Server, Long> {
	
	Optional<Server> findOneByName(String name);
	
	Optional<Server> findOneById(Long id);

    Optional<Server> findOneByOs(String os);

    Optional<Server> findOneByStatus(String status);
    
    @Query("select s from Server s where s.user.login =:login")
    Page<Server> findBy(@Param("login") String login, Pageable pageable);
    
    Page<Server> findAllByCreatedBy(String createdBy, Pageable pageable);

}
