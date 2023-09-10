package com.ags.backend.util;

import java.math.BigDecimal;

public class Util {
    public static BigDecimal formatBigDecimalWithDecimalPlace(String value){
        BigDecimal bigDecimals = new BigDecimal(value);
        bigDecimals = bigDecimals.setScale(
                4, BigDecimal.ROUND_HALF_EVEN);
        return bigDecimals;
    }
}
