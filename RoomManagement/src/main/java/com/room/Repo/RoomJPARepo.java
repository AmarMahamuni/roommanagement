package com.room.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.room.model.RoomModel;

public interface RoomJPARepo extends JpaRepository<RoomModel, Long>{

}
