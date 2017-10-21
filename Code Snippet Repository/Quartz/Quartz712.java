    public void testTranslate() {

        TimeZone tz1 = TimeZone.getTimeZone("GMT-2:00");
        TimeZone tz2 = TimeZone.getTimeZone("GMT-4:00");

        Calendar vc = Calendar.getInstance(tz1);
        vc.set(Calendar.YEAR, 2013);
        vc.set(Calendar.MONTH, Calendar.JUNE);
        vc.set(Calendar.DAY_OF_MONTH, 1);
        vc.set(Calendar.HOUR_OF_DAY, 10);
        vc.set(Calendar.MINUTE, 33);
        vc.set(Calendar.SECOND, 12);
        vc.set(Calendar.MILLISECOND, 0);

        vc.setTime( translateTime(vc.getTime(), tz1, tz2) );
        assertEquals("DateBuilder-produced date is not as expected.", 12, vc.get(Calendar.HOUR_OF_DAY));

        vc = Calendar.getInstance(tz2);
        vc.set(Calendar.YEAR, 2013);
        vc.set(Calendar.MONTH, Calendar.JUNE);
        vc.set(Calendar.DAY_OF_MONTH, 1);
        vc.set(Calendar.HOUR_OF_DAY, 10);
        vc.set(Calendar.MINUTE, 33);
        vc.set(Calendar.SECOND, 12);
        vc.set(Calendar.MILLISECOND, 0);

        vc.setTime( translateTime(vc.getTime(), tz2, tz1) );
        assertEquals("DateBuilder-produced date is not as expected.", 8, vc.get(Calendar.HOUR_OF_DAY));
    }
