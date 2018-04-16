package sample.Models;

import java.util.Date;

public class Task {
    String Title, Notes, ETime, Tags, Category;
    Date start, finish;
    char Priority;

    public Task(String title, String notes, String ETime, String tags, String category, Date start, Date finish, char priority) {
        Title = title;
        Notes = notes;
        this.ETime = ETime;
        Tags = tags;
        Category = category;
        this.start = start;
        this.finish = finish;
        Priority = priority;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getETime() {
        return ETime;
    }

    public void setETime(String ETime) {
        this.ETime = ETime;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public char getPriority() {
        return Priority;
    }

    public void setPriority(char priority) {
        Priority = priority;
    }
}
