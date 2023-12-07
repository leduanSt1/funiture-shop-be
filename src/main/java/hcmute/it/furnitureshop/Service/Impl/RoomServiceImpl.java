package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Room;
import hcmute.it.furnitureshop.Repository.RoomRepository;
import hcmute.it.furnitureshop.Service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Iterable<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(Integer roomId) {
        return roomRepository.findById(roomId);
    }
}
