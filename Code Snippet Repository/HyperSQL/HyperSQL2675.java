    private static String getIdName(String s) {

        int nameStart = s.indexOf('=') + 1;

        if (nameStart < 1) {
            return null;
        }

        if (nameStart == s.length()) {
            throw new RuntimeException(
                "Leave off '=' if you do not want to name a connection");
        }

        return s.substring(nameStart);
    }
