package com.j.dbl.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;


public class DoubleUtil {

	public static String formatDoubleNumber(double value) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		return bd.toPlainString();
	}
	
	/**
	 * 保留二位小数点
	 * @param d
	 * @return 如果为空返回0
	 */
	public static Double retainmDecimal2(Double d) {
		if(StringUtil.isEmpty(d))
			return 0D;
		
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	   /**
     * 截取小数点
     * @param val  要截取的数
     * @param decimal 位数
     * @return
     */
    public static double changeDouble(Double val,Integer decimal){
        String format="#";
        if(decimal!=null && decimal>0){
            for(int i=0;i<decimal;i++){
                if(i==0){
                    format+=".0";
                }else{
                    format+="0";
                }
            }
        }
        DecimalFormat df = new DecimalFormat(format);
        return Double.valueOf(df.format(val));
   }
	
    /**
     * 
     * @param min
     * @param max
     */
    public static double getRandom(Double min, Double max){
        Random random = new Random();
        Double s = random.nextDouble()*(max-min) + min;
        return CurrencyUtil.round(s, 2);
    }
//	public static void main(String[] args){
//		System.out.println(DoubleUtil.retainmDecimal2(11.11111));
//	}
}
