	public String browse() throws Exception {
		Calendar cal = GregorianCalendar.getInstance();
		now = cal.getTime();
		cal.roll(Calendar.DATE, -1);
		cal.roll(Calendar.HOUR, -3);
		past = cal.getTime();
		cal.roll(Calendar.DATE, 2);
		future = cal.getTime();

		cal.roll(Calendar.YEAR, -1);
		before = cal.getTime();

		cal.roll(Calendar.YEAR, 2);
		after = cal.getTime();
		return SUCCESS;
	}
