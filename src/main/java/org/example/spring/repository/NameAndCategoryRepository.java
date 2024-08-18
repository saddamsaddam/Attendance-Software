package org.example.spring.repository;
import org.example.spring.model.NameAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface NameAndCategoryRepository extends JpaRepository<NameAndCategory, Long> {
    // You can add custom query methods here if needed
    // select query for all data according to type, status
    @Query("SELECT nc FROM NameAndCategory nc WHERE nc.type = :type AND nc.status = :status")
    List<NameAndCategory> findAllByTypeAndStatus(@Param("type") String type, @Param("status") String status);

    // update name , presentTime according to name  ,type , status
    @Modifying
    @Transactional
    @Query("UPDATE NameAndCategory nc SET nc.name = :newName, nc.presentTime = :newPresentTime WHERE nc.name = :oldName AND nc.type = :type AND nc.status = :status")
    void updateNameAndPresentTimeByTypeAndStatus(@Param("oldName") String oldName,
                                                 @Param("newName") String newName,
                                                 @Param("newPresentTime") LocalDateTime newPresentTime,
                                                 @Param("type") String type,
                                                 @Param("status") String status);
    // update status according to presentTime
    @Modifying
    @Transactional
    @Query("UPDATE NameAndCategory nc SET nc.status = :newStatus WHERE nc.presentTime = :presentTime")
    void updateStatusByPresentTime(@Param("presentTime") LocalDateTime presentTime, @Param("newStatus") String newStatus);

}
