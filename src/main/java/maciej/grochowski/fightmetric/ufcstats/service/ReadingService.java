package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.ufcstats.dto.FighterDTO;
import maciej.grochowski.fightmetric.ufcstats.dto.FinalDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ReadingService {

    void initData();

    List<List<FighterDTO>> getFightersList();

    List<FinalDTO> getFinalDTOList();

    List<List<FighterDTO>> getAllFightersFromUFCStats();

    List<FighterDTO> getFightersListFromFightURL(String fightURL) throws IOException;

    String getDataFromParagraph(String line);

    BigDecimal convertToCm(String measure, Boolean isReach);

    BigDecimal getBigDecimalFromAttributesIndex(int sapmIndex);

    BufferedReader getReaderFromURL(String link) throws IOException;
}
