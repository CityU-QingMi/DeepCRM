    private static String getResourceString(int code) {

        String key = StringUtil.toZeroPaddedString(code, SQL_CODE_DIGITS,
            SQL_CODE_DIGITS);
        String string = ResourceBundleHandler.getString(bundleHandle, key);

        if (string == null) {
            string = defaultMessage;
        }

        return string;
    }
