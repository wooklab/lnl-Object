package com.wooklab.example.chapter02.discount.impl;

import com.wooklab.example.chapter02.Money;
import com.wooklab.example.chapter02.Screening;
import com.wooklab.example.chapter02.discount.DiscountPolicy;

public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
