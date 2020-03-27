package com.room.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.room.model.RoomAddressModel;

public interface RoomAddressJpaRepo extends JpaRepository<RoomAddressModel, Long>{

}
