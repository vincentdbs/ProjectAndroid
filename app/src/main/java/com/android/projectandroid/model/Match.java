package com.android.projectandroid.model;

public class Match {
    private int idLogoTeamDom, idLogoTeamExt;
    private String nameTeamDom, nameTeamExt, scoreTeamDom, scoreTeamExt, time;

    public Match(int idLogoTeamDom, int idLogoTeamExt, String nameTeamDom, String nameTeamExt, String scoreTeamDom, String scoreTeamExt, String time) {
        this.idLogoTeamDom = idLogoTeamDom;
        this.idLogoTeamExt = idLogoTeamExt;
        this.nameTeamDom = nameTeamDom;
        this.nameTeamExt = nameTeamExt;
        this.scoreTeamDom = scoreTeamDom;
        this.scoreTeamExt = scoreTeamExt;
        this.time = time;
    }

    public int getIdLogoTeamDom() {
        return idLogoTeamDom;
    }

    public int getIdLogoTeamExt() {
        return idLogoTeamExt;
    }

    public String getNameTeamDom() {
        return nameTeamDom;
    }

    public String getNameTeamExt() {
        return nameTeamExt;
    }

    public String getScoreTeamDom() {
        return scoreTeamDom;
    }

    public String getScoreTeamExt() {
        return scoreTeamExt;
    }

    public String getTime() {
        return time;
    }

}
