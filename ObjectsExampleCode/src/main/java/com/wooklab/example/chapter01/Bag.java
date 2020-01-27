package com.wooklab.example.chapter01;

/**
 * 관람객 소지품을 보관할 가방
 */
public class Bag {
    /** 현금 */
    private Long amount;
    /** 초대장 */
    private Invitation invitation;
    /** 티켓 */
    private Ticket ticket;

    /**
     * 초대장 없이 현금만 보관
     * @param amount
     */
    public Bag(Long amount) {
        this(null, amount);
    }

    /**
     * 현금과 초대장을 함께 보관
     * @param invitation
     * @param amount
     */
    public Bag(Invitation invitation, Long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    /**
     * 초대장 보유 여부
     * @return
     */
    public boolean hasInvitation() {
        return invitation != null;
    }

    /**
     * 티켓 소유 여부
     * @return
     */
    public boolean hasTicket() {
        return ticket != null;
    }

    /**
     * 초대장을 티켓으로 교환
     * @param ticket
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
