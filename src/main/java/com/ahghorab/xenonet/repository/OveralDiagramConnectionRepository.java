package com.ahghorab.xenonet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahghorab.xenonet.domain.Authority;
import com.ahghorab.xenonet.domain.OveralDiagramConnection;

public interface OveralDiagramConnectionRepository extends JpaRepository<OveralDiagramConnection, Long> {

}
