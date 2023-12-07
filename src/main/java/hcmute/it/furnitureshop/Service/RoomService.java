package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Room;

import java.util.Optional;

public interface RoomService {
    public Iterable<Room> getAll();
    public Optional<Room> getById(Integer roomId);

}
