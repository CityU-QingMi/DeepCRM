    private static String convertNumericEscapes(String string) {
        String workString = string;
        int i = 0;

        for (char dig = '0'; dig <= '9'; dig++) {
            while ((i = workString.indexOf("\\" + dig, i)) > -1
                    && i < workString.length() - 1)
                workString = convertNumericEscape(string, i);
            while ((i = workString.indexOf("\\x" + dig, i)) > -1
                    && i < workString.length() - 1)
                workString = convertNumericEscape(string, i);
            while ((i = workString.indexOf("\\X" + dig, i)) > -1
                    && i < workString.length() - 1)
                workString = convertNumericEscape(string, i);
        }
        return workString;
    }
