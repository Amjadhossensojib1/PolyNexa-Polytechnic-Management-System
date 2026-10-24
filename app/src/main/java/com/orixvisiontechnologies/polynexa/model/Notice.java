package com.orixvisiontechnologies.polynexa.model;


import java.util.Objects;

public class Notice {

    private final String id;
    private final String schoolName;
    private final String postedDate;   // e.g. "6 Chaitra, 2080"
    private final String title;
    private final String description;
    private final String eventDate;    // optional (null ba empty hote pare)
    private final String eventTime;    // optional
    private final String pdfUrl;       // optional (null ba empty hole PDF box dekhabe na)

    public Notice(String id, String schoolName, String postedDate, String title,
                  String description, String eventDate, String eventTime, String pdfUrl) {
        this.id = id;
        this.schoolName = schoolName;
        this.postedDate = postedDate;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.pdfUrl = pdfUrl;
    }

    public String getId() { return id; }
    public String getSchoolName() { return schoolName; }
    public String getPostedDate() { return postedDate; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getEventDate() { return eventDate; }
    public String getEventTime() { return eventTime; }
    public String getPdfUrl() { return pdfUrl; }

    public boolean hasPdf() {
        return pdfUrl != null && !pdfUrl.trim().isEmpty();
    }

    public boolean hasEventDate() {
        return eventDate != null && !eventDate.trim().isEmpty();
    }

    public boolean hasEventTime() {
        return eventTime != null && !eventTime.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice)) return false;
        Notice n = (Notice) o;
        return Objects.equals(id, n.id)
                && Objects.equals(schoolName, n.schoolName)
                && Objects.equals(postedDate, n.postedDate)
                && Objects.equals(title, n.title)
                && Objects.equals(description, n.description)
                && Objects.equals(eventDate, n.eventDate)
                && Objects.equals(eventTime, n.eventTime)
                && Objects.equals(pdfUrl, n.pdfUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schoolName, postedDate, title, description, eventDate, eventTime, pdfUrl);
    }
}