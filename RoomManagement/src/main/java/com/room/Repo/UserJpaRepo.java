package com.room.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.room.model.RoomModel;
import com.room.model.UserModel;

public interface UserJpaRepo extends JpaRepository<UserModel, Long>{

	Optional<UserModel> findByEmail(String email);

    //UserModel findByEmailIdIgnoreCase(String emailId);

	List<RoomModel> findByRoom(long l);


}
