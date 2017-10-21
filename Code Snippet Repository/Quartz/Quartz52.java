    public static Date evenSecondDateBefore(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }
