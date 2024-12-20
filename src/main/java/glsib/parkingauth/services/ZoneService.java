package glsib.parkingauth.services;

import glsib.parkingauth.entities.Zone;
import glsib.parkingauth.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }

    public Optional<Zone> findByLatitudeAndLongitude(double latitude, double longitude) {return zoneRepository.findByLatitudeAndLongitude(latitude, longitude);}

    public Optional<Zone> findById(Long zoneId) {return zoneRepository.findById(zoneId);}
}
