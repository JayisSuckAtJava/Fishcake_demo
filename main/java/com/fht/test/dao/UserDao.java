package com.fht.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fht.test.dto.UserDto;

@Repository
public interface UserDao extends JpaRepository<UserDto, Integer> {

	@Query(value = "select u_index, u_id, u_pw, u_info, sys_index, u_class from tb_u where u_id = :inputEmail and u_pw = password(:inputPassword) ", nativeQuery = true)
	UserDto findByIdAndPassword(String inputEmail, String inputPassword);

	UserDto findById(String id);
	
	@Query(value = "insert into tb_u (u_id, u_pw, u_class, u_info, sys_index) values (:#{#user.id}, password(:#{#user.password}), 1, :#{#user.info}, :#{#user.systemDto})", nativeQuery = true)
	void saveWithPassword(UserDto user);

}
