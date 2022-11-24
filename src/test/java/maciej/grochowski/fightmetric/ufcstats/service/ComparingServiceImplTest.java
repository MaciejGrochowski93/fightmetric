package maciej.grochowski.fightmetric.ufcstats.service;

import maciej.grochowski.fightmetric.pinnacle.entity.EventDB;
import maciej.grochowski.fightmetric.pinnacle.repository.EventRepository;
import maciej.grochowski.fightmetric.pinnacle.service.EventServiceImpl;
import maciej.grochowski.fightmetric.ufcstats.dto.FighterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.valueOf;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class ComparingServiceImplTest {

    private List<EventDB> eventList;
    private FighterDTO fighter1;
    private FighterDTO fighter2;
    private FighterDTO fighter3;
    private FighterDTO fighter4;
    private FighterDTO fighter5;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void prepareEvents() {
        LocalDate date1 = LocalDate.of(2000, 5, 25);
        LocalDate date2 = LocalDate.of(1998, 4, 20);
        LocalDate date3 = LocalDate.of(1994, 3, 15);
        LocalDate date4 = LocalDate.of(1990, 2, 10);
        LocalDate date5 = LocalDate.of(1986, 1, 5);


        fighter1 = new FighterDTO("Ngannou", valueOf(198), valueOf(200), valueOf(5.5), valueOf(0.50),
                valueOf(3), valueOf(70), valueOf(3), valueOf(80), valueOf(60), date1);

        fighter2 = new FighterDTO("Rakic", valueOf(190), valueOf(186), valueOf(4.5), valueOf(0.50),
                valueOf(3), valueOf(65), valueOf(3), valueOf(70), valueOf(55), date2);

        fighter3 = new FighterDTO("Whittaker", valueOf(182), valueOf(180), valueOf(3.75), valueOf(0.50),
                valueOf(3), valueOf(60), valueOf(3), valueOf(60), valueOf(50), date3);

        fighter4 = new FighterDTO("Edwards", valueOf(176), valueOf(170), valueOf(3.25), valueOf(0.50),
                valueOf(3), valueOf(55), valueOf(3), valueOf(55), valueOf(45), date4);

        fighter5 = new FighterDTO("Edgar", valueOf(170), valueOf(165), valueOf(2.75), valueOf(0.50),
                valueOf(3), valueOf(40), valueOf(3), valueOf(50), valueOf(40), date5);
    }
}