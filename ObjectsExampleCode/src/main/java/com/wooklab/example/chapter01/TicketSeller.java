package com.wooklab.example.chapter01;

/**
 * 매표소에서 초대장을 티켓으로 교환해 주거나 티켓을 판매하는 역할을 수행하는
 * 판매원
 */
public class TicketSeller {
    /** 판매원이 일하는 매표소 */
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketOffice.getTickets();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketOffice.getTickets();
            audience.getBag().minusAmount(ticket.getFee());
            ticketOffice.plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
