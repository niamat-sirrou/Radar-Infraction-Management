package com.example.infraction.Controller;

import com.example.infraction.model.Radar;
import com.example.infraction.service.RadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radar")

public class RadarController {
    @Autowired
    private RadarService radarService;
    @PostMapping
    public Radar createRadar(@RequestBody Radar radar) {

        return radarService.saveRadarAndSendNotification(radar);
    }

    @GetMapping
    public List<Radar> getAllRadars() {
        return radarService.getAllRadars();
    }

    @GetMapping("/{id}")
    public Radar getRadarById(@PathVariable Long id) {
        return radarService.getRadarById(id);
    }
    @PutMapping("/{id}")
    public Radar updateRadar(@PathVariable Long id, @RequestBody Radar radar) {
        Radar existingRadar = radarService.getRadarById(id);
        if (existingRadar != null) {
            existingRadar.setNumRadar(radar.getNumRadar());
            existingRadar.setVitesseDetectee(radar.getVitesseDetectee());
            existingRadar.setVitesseMaxAutorisee(radar.getVitesseMaxAutorisee());
            existingRadar.setAdresse(radar.getAdresse());
            existingRadar.setMatricule(radar.getMatricule());
            existingRadar.setDateDetectee(radar.getDateDetectee());
            return radarService.saveRadar(existingRadar);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRadar(@PathVariable Long id) {
        radarService.deleteRadar(id);
    }


}

