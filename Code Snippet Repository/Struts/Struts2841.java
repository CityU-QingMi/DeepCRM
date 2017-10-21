    public static String escapeQueryString(String unescString) {
        if (unescString == null)
            return null;

        String escString = "";
        String shellSpChars = "\\\"";

        for (int index = 0; index < unescString.length(); index++) {
            char nextChar = unescString.charAt(index);

            if (shellSpChars.indexOf(nextChar) != -1)
                escString += "\\";

            escString += nextChar;
        }
        return escString;
    }
