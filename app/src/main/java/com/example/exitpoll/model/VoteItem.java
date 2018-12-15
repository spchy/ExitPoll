package com.example.exitpoll.model;

public class VoteItem {
    public final long id;
    public final int num;
    public final String image;
    public VoteItem(long id, int number, String image) {
        this.id = id;
        this.num = number;
        this.image = image;
    }
}
