package lk.ijse.spring.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean itemIdValidation(String itemId) {
        String regexForItemId = "^I00\\d+$";
        Pattern regexPattern = Pattern.compile(regexForItemId);
        return regexPattern.matcher(itemId).matches();
    }

    public static boolean customerIdValidation(String customerId) {
        String regexForCustomerId = "^C00\\d+$";
        Pattern regexPattern = Pattern.compile(regexForCustomerId);
        return regexPattern.matcher(customerId).matches();
    }
}
