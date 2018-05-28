package sample.Models;

import java.util.Date;

public class Task {

    int ID;
    String Title, User;
    int Estimated_Time;
    Date StarFrom;
    Date FinishBy;
    String Tags;
    char Priority;
    String Category;
    String Notes;
    boolean Status, Focus;

    public Task(){

    }
    public Task(int id,String title, int estimated_Time, Date starFrom, Date finishBy, String tags, char priority, String category, String notes,boolean status, boolean focus, String user) {
        ID = id;
        Title = title;
        Estimated_Time = estimated_Time;
        StarFrom = starFrom;
        FinishBy = finishBy;
        Tags = tags;
        Priority = priority;
        Category = category;
        Notes = notes;
        Status= status;
        Focus = focus;
        User = user;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getEstimated_Time() {
        return Estimated_Time;
    }

    public void setEstimated_Time(int estimated_Time) {
        Estimated_Time = estimated_Time;
    }

    public Date getStarFrom() {
        return StarFrom;
    }

    public void setStarFrom(Date starFrom) {
        StarFrom = starFrom;
    }

    public Date getFinishBy() {
        return FinishBy;
    }

    public void setFinishBy(Date finishBy) {
        FinishBy = finishBy;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public char getPriority() {
        return Priority;
    }

    public void setPriority(char priority) {
        Priority = priority;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isFocus() {
        return Focus;
    }

    public void setFocus(boolean focus) {
        Focus = focus;
    }
}
