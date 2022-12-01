package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.ufcstats.dto.FighterDetailsResponse;

public interface ComparingService {

    void setFightersAdvantages(FighterDetailsResponse fighter1, FighterDetailsResponse fighter2);
}
