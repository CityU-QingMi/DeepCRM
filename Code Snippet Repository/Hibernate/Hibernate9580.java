	@Test
	public void testCalendarDateType() {
		final Calendar original = new GregorianCalendar();
		final Calendar copy = new GregorianCalendar();
		final Calendar different = new GregorianCalendar();
		different.set( Calendar.MONTH, 9 );
		different.set( Calendar.DAY_OF_MONTH, 9 );
		different.set( Calendar.YEAR, 2999 );

		runBasicTests( CalendarDateType.INSTANCE, original, copy, different );
	}
