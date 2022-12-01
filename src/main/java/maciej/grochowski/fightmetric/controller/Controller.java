package maciej.grochowski.fightmetric.controller;

import maciej.grochowski.fightmetric.init.DataLoader;
import maciej.grochowski.fightmetric.pinnacle.entity.EventDB;
import maciej.grochowski.fightmetric.ufcstats.dto.FightSummary;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;
import maciej.grochowski.fightmetric.ufcstats.service.FinalDTOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class Controller {

    private final FinalDTOService finalDTOService;
    private final DataLoader dataLoader;

    public Controller(FinalDTOService finalDTOService, DataLoader dataLoader) {
        this.finalDTOService = finalDTOService;
        this.dataLoader = dataLoader;
    }

    @GetMapping("/odds")
    private List<EventDB> getAllEvents() {
        return finalDTOService.getEventsList();
    }

    @GetMapping("/finals")
    private List<FinalDTO> getAllFinals() {
        return dataLoader.getFinalDTOList();
    }

    @GetMapping("/fights")
    private List<FightSummary> getFightersCollection() {
        return dataLoader.getFightSummaryList();
    }
}
