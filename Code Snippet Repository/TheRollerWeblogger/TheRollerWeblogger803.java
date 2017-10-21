    public static Locale toLocale(String locale) {
        if (locale != null) {
            String[] localeStr = StringUtils.split(locale,"_");
            if (localeStr.length == 1) {
                if (localeStr[0] == null) {
                    localeStr[0] = "";
                }
                return new Locale(localeStr[0]);
            } else if (localeStr.length == 2) {
                if (localeStr[0] == null) {
                    localeStr[0] = "";
                }
                if (localeStr[1] == null) {
                    localeStr[1] = "";
                }
                return new Locale(localeStr[0], localeStr[1]);
            } else if (localeStr.length == 3) {
                if (localeStr[0] == null) {
                    localeStr[0] = "";
                }
                if (localeStr[1] == null) {
                    localeStr[1] = "";
                }
                if (localeStr[2] == null) {
                    localeStr[2] = "";
                }
                return new Locale(localeStr[0], localeStr[1], localeStr[2]);
            }
        }
        return Locale.getDefault();
    }
