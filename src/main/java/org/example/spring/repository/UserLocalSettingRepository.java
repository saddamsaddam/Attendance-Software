package org.example.spring.repository;
import org.example.spring.model.UserLocalSetting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
@Repository
public interface UserLocalSettingRepository extends CrudRepository<UserLocalSetting, Long> {
  // update status according to only status
  // Update status based on employeeId
  @Transactional
  @Modifying
  @Query("UPDATE UserLocalSetting u SET u.status = :newStatus WHERE u.employeeId = :employeeId AND u.status = :oldStatus")
  void updateStatusByEmployeeIdAndStatus(String employeeId, String oldStatus, String newStatus);

  // select all by status
  // Select all by status
  List<UserLocalSetting> findByStatus(String status);

  // update UserLocalSetting set status according to employeeId,name,currentTimee
  @Transactional
  @Modifying
  @Query("UPDATE UserLocalSetting u SET u.status = :newStatus " +
          "WHERE u.employeeId = :employeeId AND u.name = :name AND u.currentTimee = :currentTime")
  void updateStatusByEmployeeIdAndNameAndCurrentTime(String employeeId, String name, String currentTime, String newStatus);

}
