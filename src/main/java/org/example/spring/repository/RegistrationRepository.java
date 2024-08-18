package org.example.spring.repository;
import org.example.spring.model.EmployeeData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface RegistrationRepository extends CrudRepository<EmployeeData, Long> {
    // Add custom queries if needed
    Iterable<EmployeeData> findAll(); // Custom method to find all data

    // select all row data  according to  status
    // Select all row data according to the status
    @Query("SELECT e FROM EmployeeData e WHERE e.status = :status")
    List<EmployeeData> findByStatus(@Param("status") String status);


    // select all row data  according to  idNumber, name, status
    @Query("SELECT e FROM EmployeeData e WHERE e.idNumber = :idNumber AND e.name = :name AND e.status = :status")
    Iterable<EmployeeData> findByDetails(@Param("idNumber") String idNumber, @Param("name") String name, @Param("status") String status);

    // update  row data  according to  idNumber, name and set status and endDate
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeData e SET e.status = :newStatus, e.endDate = :newEndDate WHERE e.idNumber = :idNumber AND e.name = :name")
    void updateStatusAndEndDate(@Param("idNumber") String idNumber, @Param("name") String name,
                                @Param("newStatus") String newStatus, @Param("newEndDate") String newEndDate);


// update position according to idNumber, name, and status
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeData e SET e.position = :newPosition WHERE e.idNumber = :idNumber AND e.name = :name AND e.status = :status")
    void updatePosition(@Param("idNumber") String idNumber, @Param("name") String name,
                        @Param("status") String status, @Param("newPosition") String newPosition);

// update status according to currentTimee
// Update idNumber, name, joinDate, designation according to currentTimee

    // Update status according to currentTimee
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeData e SET e.status = :newStatus WHERE e.currentTimee = :currentTime")
    void updateStatusWithCurrentTime(@Param("newStatus") String newStatus, @Param("currentTime") String currentTime);

    // Define a method to update position according to idNumber, name, and status
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeData e SET e.position = :newPosition WHERE e.idNumber = :idNumber AND e.name = :name AND e.status = :status")
    void updatePositionByIdNumberAndNameAndStatus(@Param("idNumber") String idNumber, @Param("name") String name,
                                                  @Param("status") String status, @Param("newPosition") String newPosition);

}