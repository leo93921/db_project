package it.unisalento.db.project.models.dto;

import java.util.Date;

public class JobHistoryItemDto {

    private long count;
    private Date date;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
