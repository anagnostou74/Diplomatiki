package gr.mobap.simera;

public class CalendarData {
    public String date;
    public String time;
    public String type;
    public String place;
    public String details;


    public CalendarData() {
    }

    public CalendarData(String date,
                        String time,
                        String place,
                        String type,
                        String details) {

        this.date = date;
        this.time = time;
        this.type = type;
        this.place = place;
        this.details = details;

    }
}