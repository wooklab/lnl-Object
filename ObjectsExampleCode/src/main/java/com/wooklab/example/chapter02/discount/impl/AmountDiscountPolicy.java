package com.wooklab.example.chapter02.discount.impl;

import com.wooklab.example.chapter02.Money;
import com.wooklab.example.chapter02.Screening;
import com.wooklab.example.chapter02.discount.DiscountCondition;
import com.wooklab.example.chapter02.discount.DefaultDiscountPolicy;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
