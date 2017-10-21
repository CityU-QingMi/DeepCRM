    public static Date translateTime(Date date, TimeZone src, TimeZone dest) {

        Date newDate = new Date();

        int offset = (dest.getOffset(date.getTime()) - src.getOffset(date.getTime()));

        newDate.setTime(date.getTime() - offset);

        return newDate;
    }
