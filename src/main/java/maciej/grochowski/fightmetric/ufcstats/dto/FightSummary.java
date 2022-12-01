package maciej.grochowski.fightmetric.ufcstats.dto;

import java.util.List;
import java.util.Objects;

public class FightSummary {

    private List<FighterDetailsResponse> fightDetails;

    public FightSummary(List<FighterDetailsResponse> fightDetails) {
        this.fightDetails = fightDetails;
    }

    public List<FighterDetailsResponse> getFightDetails() {
        return fightDetails;
    }

    public void setFightDetails(List<FighterDetailsResponse> fightDetails) {
        this.fightDetails = fightDetails;
    }

    @Override
    public String toString() {
        return "FightSummary{" +
                "fightersDetailsList=" + fightDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FightSummary that = (FightSummary) o;
        return fightDetails.equals(that.fightDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fightDetails);
    }
}
