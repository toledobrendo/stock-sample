package com.btagila.stockserver.resource;

import com.btagila.stockserver.domain.dto.WatchDto;
import com.btagila.stockserver.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WatchResource {

    private WatchService watchService;

    @Autowired
    public WatchResource(WatchService watchService) {
        this.watchService = watchService;
    }

    @RequestMapping(value = "/watch", method = RequestMethod.POST)
    public ResponseEntity<WatchDto> saveWatch(@RequestBody WatchDto watchDto) {
        return ResponseEntity.ok(watchService.saveWatch(watchDto));
    }

    @RequestMapping(value = "/watch/delete", method = RequestMethod.POST)
    public ResponseEntity<WatchDto> deleteWatch(@RequestBody WatchDto watchDto) {
        return ResponseEntity.ok(watchService.deleteWatch(watchDto));
    }

    @RequestMapping(value = "/watch", method = RequestMethod.GET)
    public ResponseEntity<List<WatchDto>> getWatchlist() {
        return ResponseEntity.ok(watchService.getWatchlist());
    }
}
