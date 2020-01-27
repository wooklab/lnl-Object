package com.wooklab.example.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * 관람객이 소극장에 입장하기 위한 매표소
 */
public class TicketOffice {
    /** 판매 금액 */
    private Long amount;
    /** 판매하거나 교환해 줄 티켓의 목록 */
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, List<Ticket> tickets) {
        this.amount = amount;
        this.tickets = tickets;
    }

    public Ticket getTickets() {
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
