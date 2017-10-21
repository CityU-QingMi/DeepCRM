    private Date parseDateString(String value, String format) {

        SimpleDateFormat d0 = null;
        if (StringUtils.isNotEmpty(format)) {
            d0 = new SimpleDateFormat(format);
        }
        SimpleDateFormat d1 = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, Locale.getDefault());
        SimpleDateFormat d2 = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.getDefault());
        SimpleDateFormat d3 = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());
        SimpleDateFormat[] dfs = (d0 != null ? new SimpleDateFormat[]{d0, d1, d2, d3} : new SimpleDateFormat[]{d1, d2, d3});
        for (SimpleDateFormat df : dfs)
            try {
                Date check = df.parse(value);
                if (check != null) {
                    return check;
                }
            } catch (ParseException ignore) {
            }
        return null;
    }
