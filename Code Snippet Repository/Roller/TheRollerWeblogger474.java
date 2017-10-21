    private void buildDayNames() {
        // build array of names of days of week
        mDayNames = new String[7];
        Calendar dayNameCal = Calendar.getInstance(mLocale);
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEE", mLocale);
        dayNameCal.set(Calendar.DAY_OF_WEEK, dayNameCal.getFirstDayOfWeek());
        for (int dnum = 0; dnum < 7; dnum++) {
            mDayNames[dnum] = dayFormatter.format(dayNameCal.getTime());
            dayNameCal.add(Calendar.DATE, 1);
        }
    }
