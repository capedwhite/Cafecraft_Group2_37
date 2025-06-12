package model;

public class AboutCafe {
    private int id;
    private String content;

    public AboutCafe() {
        this.id = 0;
        this.content = "";
    }

    public AboutCafe(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() { return id; }
    
    public String getContent() { return content; }

    public void setId(int id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
}
