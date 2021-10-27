package com.northern.hotel.services.entity;

public class RoomModel {
    private long id;
    private String number;
    private String name;
    private String info;

    public RoomModel() {
    }

    public RoomModel(long id, String number, String name, String info) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Room translateModelToRoom() {
        Room room = new Room();
        room.setId(id);
        room.setNumber(number);
        room.setName(name);
        room.setInfo(info);
        return room;
    }
}
