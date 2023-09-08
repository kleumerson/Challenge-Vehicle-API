package com.ags.backend.util;

import java.math.BigDecimal;

public class Util {
    public static String  removePointAndComma(String oldString){
        String newString = null;
        if (oldString.contains(".") || oldString.contains(",")){
            newString = oldString.replaceAll("\\.", "");
            newString = newString.replaceAll(",", "");
        }else{
            newString = oldString;
        }
        return newString;
    }
    public static BigDecimal convertStringToBigDecimal(String valueString){
        BigDecimal valueBigDecimal = new BigDecimal(removePointAndComma(valueString));
        return valueBigDecimal;
    }
}
