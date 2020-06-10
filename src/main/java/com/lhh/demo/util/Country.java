package com.lhh.demo.util;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lai.huihui on 2020/6/8.
 */
public class Country {
    public static Map<String, String> countryMap = new HashMap<>(16);
    public static Map<String, String> countryPCRegex = new HashMap<>(16);
    public static final String NOT_NEED_POSTCODE = "*";
    static{
        //    大洋洲：澳大利亚、新西兰
        countryMap.put("AU","澳大利亚");
        countryPCRegex.put("AU", "\\d{4}");
        countryMap.put("NZ","新西兰");
        countryPCRegex.put("NZ", NOT_NEED_POSTCODE);
        //    北美：美国、加拿大
        countryMap.put("US","美国");
        countryPCRegex.put("US", "\\d{5}(-\\d{4})");
        countryMap.put("CA","加拿大");
        countryPCRegex.put("CA", "[a-zA-Z]\\d[a-zA-Z] [a-zA-Z]\\d[a-zA-Z]");
        //    欧洲：英国、法国、德国、意大利、爱尔兰、葡萄牙、西班牙
        countryMap.put("GB","英国");
        countryPCRegex.put("GB","\\w{2,4} \\w{3}");
        countryMap.put("FR","法国");
        countryPCRegex.put("FR", "\\d{5}");
        countryMap.put("IT","意大利");
        countryPCRegex.put("IT", "(I-|IT-|)\\d{5}");
        countryMap.put("DE","德国");
        countryPCRegex.put("DE", "\\d{5}");
        countryMap.put("IE","爱尔兰");
        countryPCRegex.put("IE", NOT_NEED_POSTCODE);
        countryMap.put("PT","葡萄牙");
        countryPCRegex.put("PT", "\\d{4}-\\d{3}");
        countryMap.put("ES","西班牙");
        countryPCRegex.put("ES", "\\d{5}");
    }
    public static Set<String> getAllEnCountry() {
        return countryMap.keySet();
    }

    public static Collection<String> getAllChCountry() {
        return countryMap.values();
    }

    public static boolean postCodeMatch(String countryCode, String postCode) {
        String regex = countryPCRegex.get("countryCode");
        if (StringUtils.isEmpty(postCode) || StringUtils.isEmpty(regex)) {
            return false;
        }
        return postCode.matches(regex);
    }

    public static void main(String args[]) {
        String regex = "\\d{5}(|-\\d{4})";
        System.out.println("12345-1234".matches(regex));
    }
}
