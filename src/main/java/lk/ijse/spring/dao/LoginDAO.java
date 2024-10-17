package lk.ijse.spring.dao;

import lk.ijse.spring.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDAO extends JpaRepository<UserEntity, String> {
}
