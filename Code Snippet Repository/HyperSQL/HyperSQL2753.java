    static protected void expandStamps(StringBuffer sb) {

        int i = sb.indexOf(TIMESTAMP_VAR_STR);

        if (i < 1) {
            return;
        }

        String timestamp;

        synchronized (sdfYMDHMS) {
            timestamp = sdfYMDHMS.format(new java.util.Date());
        }

        while (i > -1) {
            sb.replace(i, i + TIMESTAMP_VAR_STR.length(), timestamp);

            i = sb.indexOf(TIMESTAMP_VAR_STR);
        }
    }
