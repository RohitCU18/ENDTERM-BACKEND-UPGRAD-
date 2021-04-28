package ua.telecom.phonebook.userrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.telecom.phonebook.userrecords.model.UserRecord;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudUserRecordRepository extends JpaRepository<UserRecord, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserRecord ur WHERE ur.id=:id AND ur.user.id=:userId")
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT ur FROM UserRecord ur WHERE ur.user.id=:userId")
    List<UserRecord> findByUserId(@Param("userId")int userId);
}
