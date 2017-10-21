	@Test
	public void testCalendarType() {
		final long now = System.currentTimeMillis();
		final Calendar original = new GregorianCalendar();
		original.clear();
		original.setTimeInMillis( now );
		final Calendar copy = new GregorianCalendar();
		copy.clear();
		copy.setTimeInMillis( now );
		final Calendar different = new GregorianCalendar();
		different.setTimeInMillis( now + 9999 );

		runBasicTests( CalendarType.INSTANCE, original, copy, different );
	}
