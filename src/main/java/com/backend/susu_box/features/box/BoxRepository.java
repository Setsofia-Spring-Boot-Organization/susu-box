package com.backend.susu_box.features.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoxRepository extends JpaRepository<BoxEntity, String>, JpaSpecificationExecutor<BoxEntity> { }
