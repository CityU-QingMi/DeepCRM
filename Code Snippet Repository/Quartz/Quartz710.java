    public void testGivenBuilders() {

        Calendar vc = Calendar.getInstance();

        vc.set(Calendar.SECOND, 54);
        vc.set(Calendar.MINUTE, 13);
        vc.set(Calendar.HOUR_OF_DAY, 8);
        Date bd = nextGivenMinuteDate(vc.getTime(), 45);
        vc.setTime(bd);
        assertEquals("DateBuilder-produced date is not as expected.", 8, vc.get(Calendar.HOUR_OF_DAY));
        assertEquals("DateBuilder-produced date is not as expected.", 45, vc.get(Calendar.MINUTE));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.SECOND));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.MILLISECOND));

        vc.set(Calendar.SECOND, 54);
        vc.set(Calendar.MINUTE, 46);
        vc.set(Calendar.HOUR_OF_DAY, 8);
        bd = nextGivenMinuteDate(vc.getTime(), 45);
        vc.setTime(bd);
        assertEquals("DateBuilder-produced date is not as expected.", 9, vc.get(Calendar.HOUR_OF_DAY));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.MINUTE));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.SECOND));
        assertEquals("DateBuilder-produced date is not as expected.", 0, vc.get(Calendar.MILLISECOND));
    }
