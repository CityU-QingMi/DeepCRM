	private Date getDate(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTimeZone(UTC);
		cal.clear();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		return cal.getTime();
	}
