package org.example.spring.repository;
import org.example.spring.model.UserGlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserGlobalSettingRepository extends JpaRepository<UserGlobalSetting, Long> {
// select all according to status
// Select all entries based on the status using a JPQL query
@Query("SELECT u FROM UserGlobalSetting u WHERE u.status = :status")
List<UserGlobalSetting> findAllByStatus(String status);

// update status according to status
// Update status for all entries based on the current status using a JPQL query
@Modifying
@Transactional
@Query("UPDATE UserGlobalSetting u SET u.status = :newStatus WHERE u.status = :oldStatus")
void updateStatusByOldStatus(String oldStatus, String newStatus);

// update status accoding to cuurentimee
// Update status based on the current time
@Modifying
@Transactional
@Query("UPDATE UserGlobalSetting u SET u.status = :newStatus WHERE u.currentTimee = :currentTime")
void updateStatusByCurrentTime(String currentTime, String newStatus);

}
