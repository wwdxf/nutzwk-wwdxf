package cn.wizzer.app.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.nutz.ioc.loader.annotation.IocBean;

import java.math.BigDecimal;

/**
 * Created by wizzer on 2017/3/31.
 */
@IocBean
public class MoneyUtil {
    /**
     * 分转换为元
     *
     * @param fen 分
     * @return 元
     */
    public static String fenToYuan(int fen) {
        try {
            return new BigDecimal(fen).divide(new BigDecimal(100)).setScale(2).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 元转换为分
     *
     * @param yuan 元
     * @return 分
     */
    public static long yuanToFen(String yuan) {
        try {
            return BigDecimal.valueOf(Double.valueOf(yuan)).multiply(new BigDecimal(100)).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 元转换为分
     *
     * @param yuan 元
     * @return 分
     */
    public static int yuanToFeni(String yuan) {
        try {
            return BigDecimal.valueOf(Double.valueOf(yuan)).multiply(new BigDecimal(100)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 计算两个价格折扣率
     *
     * @param price1
     * @param price2
     * @return
     */
    public static String rate(int price1, int price2) {
        try {
            BigDecimal num1 = new BigDecimal(price1 * 10);
            BigDecimal num2 = new BigDecimal(price2);
            BigDecimal num3 = num1.divide(num2, 1, BigDecimal.ROUND_HALF_UP);
            return num3.toString().replace(".0", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "无";
    }

    /**
     * 月套餐天数计算
     * @param price1
     * @param price2
     * @return
     */
    public static String rateMonthPackage(int price1, int price2) {
        try {
            BigDecimal num1 = new BigDecimal(price1);
            BigDecimal num2 = new BigDecimal(price2);
            BigDecimal num3 = num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP);
            return num3.toString().replace(".0", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "无";
    }
    /**
     * 计算折扣后价格
     *
     * @param price
     * @param rate
     * @return
     */
    public static int getRatePrice(int price, int rate) {
        if (rate > 0 && rate < 100) {
            try {
                BigDecimal num1 = new BigDecimal(price * rate);
                BigDecimal num2 = new BigDecimal(100);
                BigDecimal num3 = num1.divide(num2, 0, BigDecimal.ROUND_HALF_UP);
                return num3.intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    /**
     * 获取折扣价格，元为单位 结果保留两位小数
     * @param price
     * @param rate
     * @return
     */
    public static double getRatePriceDouble(double price, double rate) {
        if (rate > 0 && rate < 1d) {
            try {
                BigDecimal num1 = new BigDecimal(price * rate * 100);
                BigDecimal num2 = new BigDecimal(100);
                BigDecimal num3 = num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP);
                return num3.doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    /**
     * 乘法
     * @param price1
     * @param price2
     * @return
     */
    public static double multiply(double price1, double price2) {
        try {
            BigDecimal num1 = new BigDecimal(price1 * price2 * 10000);
            BigDecimal num2 = new BigDecimal(10000);
            BigDecimal num3 = num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP);
            return num3.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算折扣后价格
     * @param price
     * @param rate
     * @return
     */
    public static int getRatePriceByString(String price, String rate) {
        Double priceD = NumberUtils.toDouble(price);
        Double rateD = NumberUtils.toDouble(rate) * 100d;

        return getRatePrice(priceD.intValue(), rateD.intValue());
    }

    /**
     * 计算分成
     *
     * @param price
     * @param rate
     * @return
     */
    public static int getFcRatePrice(int price, int rate) {
        if (rate > 0 && rate < 100) {
            try {
                BigDecimal num1 = new BigDecimal(price * rate);
                BigDecimal num2 = new BigDecimal(100);
                BigDecimal num3 = num1.divide(num2, 0, BigDecimal.ROUND_HALF_UP);
                return num3.intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 分转换为万元
     *
     * @param fen 分
     * @return 元
     */
    public static String fenToWan(int fen) {
        try {
            return new BigDecimal(fen).divide(new BigDecimal(1000000)).setScale(2).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    /**
     * 万元转换为分
     *
     * @param yuan 元
     * @return 分
     */
    public static int wanToFen(String yuan) {
        try {
            return BigDecimal.valueOf(Double.valueOf(yuan)).multiply(new BigDecimal(1000000)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
