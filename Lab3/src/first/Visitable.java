package first;

public interface Visitable {
    public boolean isVisitable(String date);
    public boolean isVisitableDMY(int day, int month, int year);
    public String getVisitingTimes();
}
