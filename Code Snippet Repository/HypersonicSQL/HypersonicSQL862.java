    public SimpleDateFormat getSimpleDateFormatGMT() {

        if (simpleDateFormatGMT == null) {
            simpleDateFormatGMT = new SimpleDateFormat("MMMM", Locale.ENGLISH);

            simpleDateFormatGMT.setCalendar(getCalendarGMT());
        }

        return simpleDateFormatGMT;
    }
