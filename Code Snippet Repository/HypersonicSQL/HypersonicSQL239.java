    public static long getTimestampSeconds(String s) {

        try {
            synchronized (sdfts) {
                java.util.Date d = sdfts.parse(s);

                return d.getTime() / 1000;
            }
        } catch (Exception e) {
            throw Error.error(ErrorCode.X_22007);
        }
    }
