    public void testBuilder() {

        Calendar vc = Calendar.getInstance();
        vc.set(Calendar.YEAR, 2013);
        vc.set(Calendar.MONTH, Calendar.JULY);
        vc.set(Calendar.DAY_OF_MONTH, 1);
        vc.set(Calendar.HOUR_OF_DAY, 10);
        vc.set(Calendar.MINUTE, 30);
        vc.set(Calendar.SECOND, 0);
        vc.set(Calendar.MILLISECOND, 0);

        Date bd = newDate().inYear(2013).inMonth(JULY).onDay(1).atHourOfDay(10).atMinute(30).atSecond(0).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);

        bd = newDate().inYear(2013).inMonthOnDay(JULY, 1).atHourMinuteAndSecond(10, 30, 0).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);


        TimeZone tz = TimeZone.getTimeZone("GMT-4:00");
        Locale lz = Locale.TAIWAN;
        vc = Calendar.getInstance(tz, lz);
        vc.set(Calendar.YEAR, 2013);
        vc.set(Calendar.MONTH, Calendar.JUNE);
        vc.set(Calendar.DAY_OF_MONTH, 1);
        vc.set(Calendar.HOUR_OF_DAY, 10);
        vc.set(Calendar.MINUTE, 33);
        vc.set(Calendar.SECOND, 12);
        vc.set(Calendar.MILLISECOND, 0);

        bd = newDate().inYear(2013).inMonth(JUNE).onDay(1).atHourOfDay(10).atMinute(33).atSecond(12).inTimeZone(tz).inLocale(lz).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);

        bd = newDateInLocale(lz).inYear(2013).inMonth(JUNE).onDay(1).atHourOfDay(10).atMinute(33).atSecond(12).inTimeZone(tz).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);

        bd = newDateInTimezone(tz).inYear(2013).inMonth(JUNE).onDay(1).atHourOfDay(10).atMinute(33).atSecond(12).inLocale(lz).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);

        bd = newDateInTimeZoneAndLocale(tz, lz).inYear(2013).inMonth(JUNE).onDay(1).atHourOfDay(10).atMinute(33).atSecond(12).build();
        assertEquals("DateBuilder-produced date is not as expected.", vc.getTime(), bd);

    }
