package ua.telecom.phonebook.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.telecom.phonebook.users.model.User;


@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE from User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User findByPhoneNumber(String phoneNumber);
}
