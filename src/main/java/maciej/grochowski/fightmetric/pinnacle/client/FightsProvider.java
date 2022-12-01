package maciej.grochowski.fightmetric.pinnacle.client;

import feign.FeignException;
import maciej.grochowski.fightmetric.pinnacle.dto.EventDTO;
import maciej.grochowski.fightmetric.pinnacle.exception.TooManyRequestsException;
import maciej.grochowski.fightmetric.pinnacle.mapper.EventMapper;
import maciej.grochowski.fightmetric.pinnacle.repository.EventRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FightsProvider {

    private final FightsClient fightsClient;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    private final static Logger log = LoggerFactory.getLogger(FightsProvider.class);

    public FightsProvider(FightsClient fightsClient, EventRepository eventRepository) {
        this.fightsClient = fightsClient;
        this.eventRepository = eventRepository;
    }

    public void fetchMarketsFromPinnacle() {
        log.info("Attempting to download UFC events from pinnacle.");
        try {
            List<EventDTO> events = fightsClient.getUFCMarkets().getEvents();
            events.stream()
                    .filter(dto -> dto.getPeriods().getNumber().getMoneyLine() != null)
                    .forEach(dto -> eventRepository.save(eventMapper.dtoToEventDB(dto))
                    );
            log.info("Pinnacle data has been fetched successfully.");
        } catch (FeignException.TooManyRequests e) {
            log.error(TooManyRequestsException.errorMessage);
        }
    }
}
