package com.wooklab.example.chapter02.discount.impl;

import com.wooklab.example.chapter02.Screening;
import com.wooklab.example.chapter02.discount.DiscountCondition;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
