package com.example.c_fest;

import java.util.Date;

public class recycler_home {
    private String cllg_edit ;
    private String date_pick ;
    private String fest_edit;
    private  String time_edit;
    private String venue_edit;

    public recycler_home() {
    }


    public recycler_home(String cllg_edit, String date_pick, String fest_edit, String time_edit, String venue_edit) {
        this.cllg_edit = cllg_edit;
        this.date_pick = date_pick;
        this.fest_edit = fest_edit;
        this.time_edit = time_edit;
        this.venue_edit = venue_edit;
    }

    public String getCllg_edit() {
        return cllg_edit;
    }

    public void setCllg_edit(String cllg_edit) {
        this.cllg_edit = cllg_edit;
    }

    public String getDate_pick() {
        return date_pick;
    }

    public void setDate_pick(String date_pick) {
        this.date_pick = date_pick;
    }

    public String getFest_edit() {
        return fest_edit;
    }

    public void setFest_edit(String fest_edit) {
        this.fest_edit = fest_edit;
    }

    public String getTime_edit() {
        return time_edit;
    }

    public void setTime_edit(String time_edit) {
        this.time_edit = time_edit;
    }

    public String getVenue_edit() {
        return venue_edit;
    }

    public void setVenue_edit(String venue_edit) {
        this.venue_edit = venue_edit;
    }
}
