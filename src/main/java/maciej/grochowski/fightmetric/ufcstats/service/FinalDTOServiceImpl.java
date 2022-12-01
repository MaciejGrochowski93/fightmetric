package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.pinnacle.entity.EventDB;
import maciej.grochowski.fightmetric.pinnacle.exception.TooManyRequestsException;
import maciej.grochowski.fightmetric.pinnacle.service.EventService;
import maciej.grochowski.fightmetric.ufcstats.dto.FighterDetailsResponse;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FinalDTOServiceImpl implements FinalDTOService{

    private final EventService eventService;
    private final ComparingService comparingService;
    private List<EventDB> eventsList;

    public FinalDTOServiceImpl(EventService eventService, ComparingService comparingService) {
        this.eventService = eventService;
        this.comparingService = comparingService;
    }

    @Override
    public void loadEvents() {
        eventsList = eventService.getAllEvents();
    }

    @Override
    public FinalDTO getFinalDTO(List<FighterDetailsResponse> fighters) {
        FighterDetailsResponse fighter1 = fighters.get(0);
        FighterDetailsResponse fighter2 = fighters.get(1);
        comparingService.setFightersAdvantages(fighter1, fighter2);

        FinalDTO finalDTO = new FinalDTO(
                fighter1.getName(),
                fighter1.getAdvantages(),
                fighter2.getName(),
                fighter2.getAdvantages()
        );

        for (EventDB event : eventsList) {
            if (Objects.equals(event.getFighter1(), fighter1.getName())) {
                finalDTO.setOdds1(event.getOdds1());
                finalDTO.setOdds2(event.getOdds2());
            } else if (Objects.equals(event.getFighter1(), fighter2.getName())) {
                finalDTO.setOdds1(event.getOdds2());
                finalDTO.setOdds2(event.getOdds1());
            }
        }

        return finalDTO;
    }

    @Override
    public List<EventDB> getEventsList() {
        if (eventsList.isEmpty()) {
            throw new TooManyRequestsException();
        }
        return eventsList;
    }
}
