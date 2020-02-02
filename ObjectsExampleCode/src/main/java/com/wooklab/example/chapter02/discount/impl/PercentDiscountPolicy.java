package com.wooklab.example.chapter02.discount.impl;

import com.wooklab.example.chapter02.Money;
import com.wooklab.example.chapter02.Screening;
import com.wooklab.example.chapter02.discount.DiscountCondition;
import com.wooklab.example.chapter02.discount.DefaultDiscountPolicy;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
