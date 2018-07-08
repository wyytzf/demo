package com.example.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Modifying
    @Query(value = "insert into user_in_role(uid,rid) values(?1,?2)", nativeQuery = true)
    int addRole(Long uid, Long rid);

}
