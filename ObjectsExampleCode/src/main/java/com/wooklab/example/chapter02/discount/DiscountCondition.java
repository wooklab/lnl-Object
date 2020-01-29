package com.wooklab.example.chapter02.discount;

import com.wooklab.example.chapter02.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
