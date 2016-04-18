package edu.pk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IPADDRESS_PATTERN ="^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$" ;
            /*"(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
*/
    public IPAddressValidator() {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    /**
     * Validate ip address with regular expression
     * @param ip ip address for validation
     * @return true valid ip address, false invalid ip address
     */
    public boolean validate(final String ip) {
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public static void main(String[] args) {
        IPAddressValidator validator = new IPAddressValidator();
        System.out.println(validator.validate("000.12.001.22"));
        System.out.println(validator.validate("1.256.001.22"));
        System.out.println(validator.validate("1.255.1.22"));
        System.out.println(validator.validate("0000..001.22"));
        System.out.println(validator.validate("000.01.001.22"));
        System.out.println(validator.validate("0000.a.001.22"));
        System.out.println(validator.validate("0000.1.001.22."));
    }
}

