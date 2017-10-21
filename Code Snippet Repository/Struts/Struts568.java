    private TimeZone getTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        if (timezone != null) {
            timezone = stripExpressionIfAltSyntax(timezone);
            String actualTimezone = (String) getStack().findValue(timezone, String.class);
            if (actualTimezone != null) {
                timezone = actualTimezone;
            }
            tz = TimeZone.getTimeZone(timezone);
        }
        return tz;
    }
