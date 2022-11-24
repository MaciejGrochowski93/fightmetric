package maciej.grochowski.fightmetric.controller;

import maciej.grochowski.fightmetric.pinnacle.client.FightsProvider;
import maciej.grochowski.fightmetric.pinnacle.entity.EventDB;
import maciej.grochowski.fightmetric.pinnacle.exception.TooManyRequestsException;
import maciej.grochowski.fightmetric.pinnacle.service.EventService;
import maciej.grochowski.fightmetric.ufcstats.dto.FighterDTO;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;
import maciej.grochowski.fightmetric.ufcstats.service.FinalDTOService;
import maciej.grochowski.fightmetric.ufcstats.service.ReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class Controller {

    private final EventService eventService;
    private final ReadingService readingService;
    private final FightsProvider fightsProvider;
    private final FinalDTOService finalDTOService;
    private List<List<FighterDTO>> fightersList;
    private List<FinalDTO> finalDTOList;
    private final static Logger log = LoggerFactory.getLogger(Controller.class);

    public Controller(EventService eventService, ReadingService readingService, FightsProvider fightsProvider, FinalDTOService finalDTOService) {
        this.eventService = eventService;
        this.readingService = readingService;
        this.fightsProvider = fightsProvider;
        this.finalDTOService = finalDTOService;
    }

    @GetMapping("/odds")
    private List<EventDB> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/finals")
    private List<FinalDTO> getAllFinals() {
        return readingService.getFinalDTOList();
    }

    @GetMapping("/fights")
    private List<List<FighterDTO>> getAllFights() {
        return readingService.getFightersList();
    }

    @GetMapping("/niom")
    public void niom() {
        try {
            fightsProvider.fetchMarketsFromPinnacle();
        } catch (TooManyRequestsException e) {
            log.error(e.getMessage(), e);
        }
        System.out.println("bbbb");
        finalDTOService.loadEvents();
        fightersList = readingService.getAllFightersFromUFCStats();
        finalDTOList = fightersList.stream()
                .map(finalDTOService::getFinalDTO)
                .collect(Collectors.toList());
    }
}
