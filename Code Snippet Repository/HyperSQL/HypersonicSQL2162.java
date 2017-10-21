    public String getString(String key) {
        String value = wrappedBundle.getString(key);
        if (value.length() < 1) {
            value = getStringFromFile(key);
            // For conciseness and sanity, get rid of all \r's so that \n
            // will definitively be our line breaks.
            if (value.indexOf('\r') > -1)
                value = value.replaceAll("\\Q\r\n", "\n")
                        .replaceAll("\\Q\r", "\n");
            if (value.length() > 0 && value.charAt(value.length() - 1) == '\n')
                value = value.substring(0, value.length() - 1);
        }
        return RefCapablePropertyResourceBundle.toNativeLs(value);
    }
