    public static long getDateSeconds(String s) {

        try {
            synchronized (sdfd) {
                java.util.Date d = sdfd.parse(s);

                return d.getTime() / 1000;
            }
        } catch (Exception e) {
            throw Error.error(ErrorCode.X_22007);
        }
    }
