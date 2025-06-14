package model;

public class Reservation {
    private int id;
    private String username;
    private int noOfPeople;
    private String date;
    private String time;
    private int tableNo;
    private String status;

    public Reservation(String username, int noOfPeople, String date, String time, int tableNo) {
        this.username = username;
        this.noOfPeople = noOfPeople;
        this.date = date;
        this.time = time;
        this.tableNo = tableNo;
        this.status = "pending";
    }

    public Reservation() {}

    // Getters & setters
    public String getUsername() { return username; }
    public int getNoOfPeople() { return noOfPeople; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getTableNo() { return tableNo; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
