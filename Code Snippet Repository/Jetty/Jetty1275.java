    public String doFormatDate(long date)
    {
        buf.setLength(0);
        gc.setTimeInMillis(date);

        int day_of_week = gc.get(Calendar.DAY_OF_WEEK);
        int day_of_month = gc.get(Calendar.DAY_OF_MONTH);
        int month = gc.get(Calendar.MONTH);
        int year = gc.get(Calendar.YEAR);
        int century = year / 100;
        year = year % 100;

        int hours = gc.get(Calendar.HOUR_OF_DAY);
        int minutes = gc.get(Calendar.MINUTE);
        int seconds = gc.get(Calendar.SECOND);

        buf.append(DAYS[day_of_week]);
        buf.append(',');
        buf.append(' ');
        StringUtil.append2digits(buf, day_of_month);

        buf.append(' ');
        buf.append(MONTHS[month]);
        buf.append(' ');
        StringUtil.append2digits(buf, century);
        StringUtil.append2digits(buf, year);

        buf.append(' ');
        StringUtil.append2digits(buf, hours);
        buf.append(':');
        StringUtil.append2digits(buf, minutes);
        buf.append(':');
        StringUtil.append2digits(buf, seconds);
        buf.append(" GMT");
        return buf.toString();
    }
