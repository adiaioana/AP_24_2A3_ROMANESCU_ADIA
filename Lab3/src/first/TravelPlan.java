package first;

import first.basics.Attraction;
import first.basics.Pair;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TravelPlan {
    Trip trip;
    LocalDate startDate, endDate;
    int color[] = new int[110];

    boolean bipGraph[][] = new boolean[110][110];
    int N = 0, M = 0;

    public TravelPlan() {
        trip = new Trip();
        startDate = LocalDate.now();
        endDate = startDate.plusDays(10);
    }

    public TravelPlan(int nrDays) {
        trip = new Trip(nrDays);
        startDate = LocalDate.now();
        endDate = startDate.plusDays(nrDays);
    }

    public TravelPlan(int nrDays, Trip t) {
        trip = t;
        startDate = LocalDate.now();
        endDate = startDate.plusDays(nrDays);
    }

    public void makeBipGraph() {
        int indA = 0, indD = 0;
        for (indA = 0; indA < trip.lista.size(); ++indA) {
            color[indA] = trip.lista.get(indA).color();
        }

        for (indD = 0; indD <= (endDate.getDayOfYear() - startDate.getDayOfYear()); indD++) {
            for (indA = 0; indA < trip.lista.size(); ++indA) {
                if (trip.lista.get(indA).isVisitable(startDate.plusDays(indD)) == true) {
                    bipGraph[indD][indA] = true;
                } else bipGraph[indD][indA] = false;
                M = (indA > M) ? indA : M;
            }
            N = (indD > N) ? indD : N;
        }
        N++;
        M++;
    }

    public void makeTravelPlanBonus() {
        int indA = 0, indD = 0;
        makeBipGraph();
        Vector<Integer>[] plan1 = this.Greedy1();
        indD = 1;
        System.out.println("Pentru greedy 1>");
        for (indD = 0; indD < N; indD++) {
            if (plan1[indD].size() > 0) {
                System.out.println("On the " + startDate.plusDays(indD) + " : ");
                for (Integer it : plan1[indD]) {
                    System.out.println(trip.lista.get(it).toString());
                }
            }
            indD++;
        }

        Vector<Integer>[] plan2 = this.Greedy2();
        indD = 1;
        System.out.println("Pentru greedy 1>");
        for (indD = 0; indD < N; indD++) {
            if (plan2[indD].size() > 0) {
                System.out.println("On the " + startDate.plusDays(indD) + " : ");
                for (Integer it : plan2[indD]) {
                    System.out.println(trip.lista.get(it).toString());
                }
            }
            indD++;
        }
    }

    public void makeTravelPlan() {
        int indA = 0, indD = 0;
        makeBipGraph();
        int[] plan = new int[110];
        plan = maxBipMatching();
        indD = 1;
        for (indD = 0; indD < N; indD++) {
            if (plan[indD] > -1) {
                System.out.println("On the " + startDate.plusDays(indD) + " the " + trip.lista.get(plan[indD]) + " will be visited");
            }
            indD++;
        }
    }

    public void makeColoredTravelPlan() {
        if (N == 0 && M == 0)
            makeBipGraph();
        Vector<Integer>[] plan = new Vector[110];
        Vector<Integer>[] plan1 = new Vector[110];
        plan = Greedy1();
        plan1 = Greedy2();
    }

    boolean matched(int u, boolean visited[], int match[]) {
        for (int v = 0; v < N; v++) {
            if (bipGraph[u][v] && !visited[v]) {
                visited[v] = true;
                if (match[v] < 0 || matched(match[v], visited, match)) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    int[] maxBipMatching() {
        int match[] = new int[N];

        for (int i = 0; i < N; ++i)
            match[i] = -1;

        int result = 0;
        for (int u = 0; u < M; u++) {
            boolean visited[] = new boolean[N];
            for (int i = 0; i < N; ++i)
                visited[i] = false;

            if (matched(u, visited, match))
                result++;
        }
        return match;
    }

    class TimePairComparator implements Comparator<Pair<Pair<LocalTime, LocalTime>, Integer>> {
        public int compare(Pair<Pair<LocalTime, LocalTime>, Integer> A, Pair<Pair<LocalTime, LocalTime>, Integer> B) {
            if (A.getKey().getValue().isBefore(B.getKey().getValue()) || (A.getKey().getValue().equals(B.getKey().getValue()) && A.getKey().getKey().isBefore(B.getKey().getKey())))
                return -1;
            if (A.getKey().getValue().equals(B.getKey().getValue()) && A.getKey().getKey().equals(B.getKey().getKey()))
                return 0;
            return 1;
        }
    }

    Vector<Integer>[] Greedy2() {
        int nrAttr = 4;
        Vector<Integer>[] plan = new Vector[110];
        for (int i = 0; i < N; ++i) {
            Vector<Pair<Pair<LocalTime, LocalTime>, Integer>> intervale = new Vector<Pair<Pair<LocalTime, LocalTime>, Integer>>(110);
            intervale.clear();
            for (int j = 0; j < M; ++j)
                if (bipGraph[i][j] == true) {
                    intervale.add(new Pair(trip.lista.get(j).timetable.get(startDate.plusDays(i)), j));
                }
            boolean stopper = false;
            int[] timespan = {30, 60, 15};
            Collections.sort(intervale, new TimePairComparator());
            for (int j = 0; j < intervale.size() && !stopper; j++)
                for (int e = intervale.size() - 1; e > j + 1; e--) {
                    int indJ = intervale.get(j).getValue();
                    int indE = intervale.get(e).getValue();
                    int tsJ = timespan[trip.lista.get(indJ).getInd() % nrAttr];
                    int tsE = timespan[trip.lista.get(indE).getInd() % nrAttr];
                    for (int l = j + 1; l < e; ++l) {
                        int indL = intervale.get(l).getValue();
                        if (intervale.get(j).getKey().getKey().plusMinutes(tsJ)
                                .isBefore(intervale.get(l).getKey().getValue().minusMinutes(tsJ)) &&
                                intervale.get(l).getKey().getValue().isBefore(intervale.get(e).getKey().getKey().minusMinutes(tsE))) {
                            plan[i].add(intervale.get(l).getValue());
                            plan[i].add(intervale.get(j).getValue());
                            plan[i].add(intervale.get(e).getValue());
                            stopper = true;
                        }

                    }
                }
            if (!stopper) {
                for (int j = 0; j < intervale.size() && !stopper; j++)
                    for (int e = intervale.size() - 1; e > j + 1; e--) {
                        int indJ = intervale.get(j).getValue();
                        int indE = intervale.get(e).getValue();
                        int tsJ = timespan[trip.lista.get(indJ).getInd() % nrAttr];
                        int tsE = timespan[trip.lista.get(indE).getInd() % nrAttr];

                        if (intervale.get(j).getKey().getKey().plusMinutes(tsJ)
                                .isBefore(intervale.get(e).getKey().getValue().minusMinutes(tsE))) {
                            plan[i].add(intervale.get(j).getValue());
                            plan[i].add(intervale.get(e).getValue());
                            stopper = true;
                        }
                    }
            }
            if (!stopper) {
                for (int j = 0; j < intervale.size(); j++) {
                    plan[i].add(intervale.get(j).getValue());
                    stopper = true;
                }
            }

        }
        return plan;
    }

    Vector<Integer>[] Greedy1() {
        int nrAttr = 4;
        Vector<Integer>[] plan = new Vector[110];
        boolean visited[] = new boolean[N];
        for (int i = 0; i < N; ++i) {
            Vector<Pair<Pair<LocalTime, LocalTime>, Integer>> intervale = new Vector<Pair<Pair<LocalTime, LocalTime>, Integer>>(110);
            intervale.clear();
            for (int j = 0; j < M; ++j)
                if (bipGraph[i][j] == true && visited[j] == false) {
                    intervale.add(new Pair(trip.lista.get(j).timetable.get(startDate.plusDays(i)), j));
                }
            int[] timespan = {30, 60, 15};
            Pair<LocalTime, Integer>[][] dpConfig = new Pair[110][110];

            for (int j = 0; j < intervale.size(); ++j)
                for (int config = 1; config < (1 << 3); config++)
                    dpConfig[config][j] = new Pair(LocalTime.of(0, 0), 0);

            Collections.sort(intervale, new TimePairComparator());
            // dpConfig[config][i]= { LocalTime A, prin care tine minte finalul la care se termina configuratia
            // Integer I, configuratia se termina cu intervalul cu indicele i}
            // configuratia are semn. ca un bit de 1 in repr. in B2 semnifica daca apare o attr. de tipul respectiv in solutie
            for (int j = 0; j < intervale.size(); ++j)
                dpConfig[(1 << j)][j] = new Pair(intervale.get(j).getKey().getKey().plusMinutes(timespan[trip.lista.get(intervale.get(j).getValue()).getInd() % nrAttr]), j);
            for (int j = 1; j < intervale.size(); ++j)
                for (int ante = j - 1; ante >= 0; ante--)
                    for (int config = 1; config < (1 << 3); config++) {
                        //
                        if (dpConfig[config][ante].getKey().isBefore(intervale.get(j).getKey().getKey().minusMinutes(timespan[trip.lista.get(intervale.get(j).getValue()).getInd() % 4]))) {
                            dpConfig[config | (1 << (trip.lista.get(intervale.get(j).getValue()).getInd() % 4))][j] = new Pair(
                                    (dpConfig[config][ante].getKey().isBefore(intervale.get(j).getKey().getKey())) ?
                                            intervale.get(j).getKey().getKey().plusMinutes(timespan[trip.lista.get(intervale.get(j).getValue()).getInd() % nrAttr]) : dpConfig[config][ante].getKey().plusMinutes(timespan[trip.lista.get(intervale.get(j).getValue()).getInd() % nrAttr])
                                    , intervale.get(j).getValue());
                        }
                    }
            boolean marker=false;
            for (int nrSol = 3; nrSol >= 1 && !marker; nrSol--) {
                for (int j = 1; j < intervale.size(); ++j)
                    if (dpConfig[(1 << nrSol) - 1][j].getValue() != 0) {
                        plan[i].add(intervale.get(j).getValue());
                        visited[intervale.get(j).getValue()] = true;
                        int ind = dpConfig[(1 << 3) - 1][j].getValue();
                        marker = true;
                        plan[i].add(ind);
                        for (int ante = ind - 1; ante >= 0; ante--)
                            if (intervale.get(ante).getKey().getKey().isBefore(intervale.get(j).getKey().getValue())) {
                                plan[i].add(ante);
                                break;
                            }
                        nrSol = 0;
                    }

            }

            if (marker)
                return plan;
            for (int j = 0; j < intervale.size(); j++) {
                plan[i].add(intervale.get(j).getValue());
                marker = true;
            }
        }
        return plan;

    }
}