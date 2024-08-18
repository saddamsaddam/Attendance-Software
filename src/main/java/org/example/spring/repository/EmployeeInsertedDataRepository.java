package org.example.spring.repository;
import org.example.spring.model.EmployeeInsertedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeInsertedDataRepository extends JpaRepository<EmployeeInsertedData, Long> {

    // Define a method to retrieve data by updateStatus
    List<EmployeeInsertedData> findByUpdateStatus(String updateStatus);

    // update updateStatus  according  to employeeId and entryDate
// Define a method to update updateStatus according to employeeId and entryDate
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeInsertedData e SET e.updateStatus = :updateStatus WHERE e.employeeId = :employeeId AND e.entryDate = :entryDate")
    void updateUpdateStatusByEmployeeIdAndEntryDate(String updateStatus, String employeeId, String entryDate);

    // Check if data exists for a given entryDate and employeeId
    boolean existsByEntryDateAndEmployeeId(String entryDate, String employeeId);

    // Define a method to update employeeId and name according to employeeId and name
    @Transactional
    @Modifying
    @Query("UPDATE EmployeeInsertedData e SET e.employeeId = :newEmployeeId, e.name = :newName WHERE e.employeeId = :employeeId AND e.name = :name")
    void updateEmployeeIdAndName(@Param("employeeId") String employeeId, @Param("name") String name,
                                 @Param("newEmployeeId") String newEmployeeId, @Param("newName") String newName);

}
