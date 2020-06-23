package org.DuaLipa.api;

public class Results {
    private String Week;
    private int Position;
    private int size;

    public int size(){
        return size;
    }

    public String getWeek() {
        return Week;
    }
    public void setWeek(String week) {
        Week = week;
    }

    public int getPosition() {
        return Position;
    }
    public void setPosition(int position) {
        Position = position;
    }
}