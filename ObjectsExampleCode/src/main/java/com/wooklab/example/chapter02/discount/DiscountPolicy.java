package com.wooklab.example.chapter02.discount;

import com.wooklab.example.chapter02.Money;
import com.wooklab.example.chapter02.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
