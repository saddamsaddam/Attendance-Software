package org.example.spring.view;
public class AttendanceData {
    private final String date;
    private final String entryTime;
    private final String lateDuration;
    private final String entryComment;
    private final String exitTime;
    private final String timeAfterExit;
    private final String exitComment;
    private final String outtime;
    private final String totalTimeInDay;
    private final String dayComment;
    private final String comment;

    public AttendanceData(String date, String entryTime, String lateDuration, String entryComment,
                          String exitTime, String timeAfterExit, String exitComment, String outtime,
                          String totalTimeInDay, String dayComment, String comment) {
        this.date = date;
        this.entryTime = entryTime;
        this.lateDuration = lateDuration;
        this.entryComment = entryComment;
        this.exitTime = exitTime;
        this.timeAfterExit = timeAfterExit;
        this.exitComment = exitComment;
        this.outtime = outtime;
        this.totalTimeInDay = totalTimeInDay;
        this.dayComment = dayComment;
        this.comment = comment;
    }

    // Getters and setters for fields

    public String getDate() {
        return date;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public String getLateDuration() {
        return lateDuration;
    }

    public String getEntryComment() {
        return entryComment;
    }

    public String getExitTime() {
        return exitTime;
    }

    public String getOutTime() {
        return outtime;
    }

    public String getTimeAfterExit() {
        return timeAfterExit;
    }

    public String getExitComment() {
        return exitComment;
    }

    public String getTotalTimeInDay() {
        return totalTimeInDay;
    }

    public String getDayComment() {
        return dayComment;
    }

    public String getComment() {
        return comment;
    }
}
