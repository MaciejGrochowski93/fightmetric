package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.pinnacle.entity.EventDB;
import maciej.grochowski.fightmetric.ufcstats.dto.FighterDetailsResponse;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;

import java.util.List;

public interface FinalDTOService {
    void loadEvents();

    FinalDTO getFinalDTO(List<FighterDetailsResponse> fighters);

    List<EventDB> getEventsList();
}
