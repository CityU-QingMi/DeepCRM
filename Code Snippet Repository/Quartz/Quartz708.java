    public void testBasicBuilding() {
    	
    	
    	Date t = dateOf(10, 30, 0, 1, 7, 2013);  // july 1 10:30:00 am
    	
    	Calendar vc = Calendar.getInstance();
    	vc.set(Calendar.YEAR, 2013);
    	vc.set(Calendar.MONTH, Calendar.JULY);
    	vc.set(Calendar.DAY_OF_MONTH, 1);
    	vc.set(Calendar.HOUR_OF_DAY, 10);
    	vc.set(Calendar.MINUTE, 30);
    	vc.set(Calendar.SECOND, 0);
    	vc.set(Calendar.MILLISECOND, 0);
    	
    	Date v = vc.getTime();
    	
        assertEquals("DateBuilder-produced date is not as expected.", t, v);
    }
