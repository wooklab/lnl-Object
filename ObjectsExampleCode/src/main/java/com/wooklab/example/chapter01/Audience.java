package com.wooklab.example.chapter01;

/**
 * 관람객
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }
}
