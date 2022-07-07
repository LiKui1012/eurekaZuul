package com.db.datasharding.repository;

import com.db.datasharding.model.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthDao extends JpaRepository<UserAuthEntity, Long>, JpaSpecificationExecutor<UserAuthEntity> {
}
