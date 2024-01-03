package app.vercel.practice.enums;

public enum DaysOfWeek {

    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
    private int representation;
    DaysOfWeek(int representation){this.representation = representation;}
    public int toInt(){return representation;}
}
