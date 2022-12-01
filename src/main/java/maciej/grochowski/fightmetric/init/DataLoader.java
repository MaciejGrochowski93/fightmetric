package maciej.grochowski.fightmetric.init;

import maciej.grochowski.fightmetric.pinnacle.client.FightsProvider;
import maciej.grochowski.fightmetric.ufcstats.dto.FightSummary;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;
import maciej.grochowski.fightmetric.ufcstats.service.FinalDTOService;
import maciej.grochowski.fightmetric.ufcstats.service.ReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader {

    private final FightsProvider fightsProvider;
    private final FinalDTOService finalDTOService;
    private final ReadingService readingService;
    private List<FightSummary> fightSummaryList;
    private List<FinalDTO> finalDTOList;
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    public DataLoader(FightsProvider fightsProvider, FinalDTOService finalDTOService, ReadingService readingService) {
        this.fightsProvider = fightsProvider;
        this.finalDTOService = finalDTOService;
        this.readingService = readingService;
    }

    @PostConstruct
    private void initData() {
        fightsProvider.fetchMarketsFromPinnacle();
        finalDTOService.loadEvents();

        fightSummaryList = readingService.getFightSummaries();
        finalDTOList = fightSummaryList.stream()
                .map(summary -> finalDTOService.getFinalDTO(summary.getFightDetails()))
                .collect(Collectors.toList());

        log.info("Loading complete");
    }

    public List<FinalDTO> getFinalDTOList() {
        return finalDTOList;
    }

    public List<FightSummary> getFightSummaryList() {
        return fightSummaryList;
    }
}
