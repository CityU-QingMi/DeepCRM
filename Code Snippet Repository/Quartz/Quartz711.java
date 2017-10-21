    public void testAtBuilders() {

        Calendar rd = Calendar.getInstance();
        Calendar vc = Calendar.getInstance();

        rd.setTime(new Date());
        Date bd = todayAt(10, 33, 12);
        vc.setTime(bd);
        assertEquals("DateBuilder-produced date is not as expected.", 10, vc.get(Calendar.HOUR_OF_DAY));
        assertEquals("DateBuilder-produced date is not as expected.", 33, vc.get(Calendar.MINUTE));
        assertEquals("DateBuilder-produced date is not as expected.", 12, vc.get(Calendar.SECOND));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.MILLISECOND));
        assertEquals("DateBuilder-produced date is not as expected.", rd.get(Calendar.DAY_OF_YEAR), vc.get(Calendar.DAY_OF_YEAR));

        rd.setTime(new Date());
        rd.add(Calendar.MILLISECOND, (int)MILLISECONDS_IN_DAY); // increment the day (using this means on purpose - to test const)
        bd = tomorrowAt(10, 33, 12);
        vc.setTime(bd);
        assertEquals("DateBuilder-produced date is not as expected.", 10, vc.get(Calendar.HOUR_OF_DAY));
        assertEquals("DateBuilder-produced date is not as expected.", 33, vc.get(Calendar.MINUTE));
        assertEquals("DateBuilder-produced date is not as expected.", 12, vc.get(Calendar.SECOND));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.MILLISECOND));
        assertEquals("DateBuilder-produced date is not as expected.", rd.get(Calendar.DAY_OF_YEAR), vc.get(Calendar.DAY_OF_YEAR));
    }
