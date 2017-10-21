    public static String stripSession(String url) {
        StringBuffer u = new StringBuffer(url);
        int sessionStart;
        while ((sessionStart = u.toString().indexOf(";" + Constants.SESSION_PARAMETER_NAME + "=")) != -1) {
            int sessionEnd = u.toString().indexOf(";", sessionStart + 1);
            if (sessionEnd == -1)
                sessionEnd = u.toString().indexOf("?", sessionStart + 1);
            if (sessionEnd == -1) 				// still
                sessionEnd = u.length();
            u.delete(sessionStart, sessionEnd);
        }
        return u.toString();
    }
