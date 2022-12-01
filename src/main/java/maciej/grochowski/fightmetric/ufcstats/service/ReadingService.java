package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.ufcstats.dto.FightSummary;

import java.util.List;

public interface ReadingService {

    List<FightSummary> getFightSummaries();
}
