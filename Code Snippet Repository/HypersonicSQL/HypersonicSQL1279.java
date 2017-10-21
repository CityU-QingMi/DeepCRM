    private static String convertEscapes(String string, String from, String to) {
        String workString = string;
        int i = 0;
        int fromLen = from.length();

        while ((i = workString.indexOf(from, i)) > -1
                && i < workString.length() - 1)
            workString = workString.substring(0, i) + to
                         + workString.substring(i + fromLen);
        return workString;
    }
