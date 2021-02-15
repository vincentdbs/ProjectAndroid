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

    public void setIdLogoTeamDom(int idLogoTeamDom) {
        this.idLogoTeamDom = idLogoTeamDom;
    }

    public int getIdLogoTeamExt() {
        return idLogoTeamExt;
    }

    public void setIdLogoTeamExt(int idLogoTeamExt) {
        this.idLogoTeamExt = idLogoTeamExt;
    }

    public String getNameTeamDom() {
        return nameTeamDom;
    }

    public void setNameTeamDom(String nameTeamDom) {
        this.nameTeamDom = nameTeamDom;
    }

    public String getNameTeamExt() {
        return nameTeamExt;
    }

    public void setNameTeamExt(String nameTeamExt) {
        this.nameTeamExt = nameTeamExt;
    }

    public String getScoreTeamDom() {
        return scoreTeamDom;
    }

    public void setScoreTeamDom(String scoreTeamDom) {
        this.scoreTeamDom = scoreTeamDom;
    }

    public String getScoreTeamExt() {
        return scoreTeamExt;
    }

    public void setScoreTeamExt(String scoreTeamExt) {
        this.scoreTeamExt = scoreTeamExt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
