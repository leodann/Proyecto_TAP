package sample.Models;

import java.util.Date;

public class Task {
   String Title;
   int Estimated_Time;
   Date StarFrom;
   Date FinishBy;
   String Tags;
   char Priority;
   String Category;

    public Task(String title, int estimated_Time, Date starFrom, Date finishBy, String tagas, char priority, String category) {
        Title = title;
        Estimated_Time = estimated_Time;
        StarFrom = starFrom;
        FinishBy = finishBy;
        Tags = tagas;
        Priority = priority;
        Category = category;
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

    public String getTagas() {
        return Tags;
    }

    public void setTagas(String tagas) {
        Tags = tagas;
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
}
