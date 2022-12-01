package maciej.grochowski.fightmetric.ufcstats.dto;

import java.util.List;
import java.util.Objects;

public class FightSummarySchedule {

    private List<FightSummary> collectionList;

    public List<FightSummary> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<FightSummary> collectionList) {
        this.collectionList = collectionList;
    }

    @Override
    public String toString() {
        return "FightSummarySchedule{" +
                "collectionList=" + collectionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FightSummarySchedule response = (FightSummarySchedule) o;
        return collectionList.equals(response.collectionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionList);
    }
}
