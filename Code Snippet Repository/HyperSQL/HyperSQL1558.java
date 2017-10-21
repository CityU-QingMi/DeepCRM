    public static void getTimestampString(StringBuffer sb, long seconds,
                                          int nanos, int scale) {

        synchronized (sdfts) {
            tempDate.setTime(seconds * 1000);
            sb.append(sdfts.format(tempDate));

            if (scale > 0) {
                sb.append('.');
                sb.append(StringUtil.toZeroPaddedString(nanos, 9, scale));
            }
        }
    }
