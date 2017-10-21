	@Test
	public void testDateType() {
		final long now = System.currentTimeMillis();
		final java.sql.Date original = new java.sql.Date( now );
		final java.sql.Date copy = new java.sql.Date( now );
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.setTimeInMillis( now );
		cal.add( Calendar.YEAR, 1 );
		final java.sql.Date different = new java.sql.Date( cal.getTime().getTime() );

		runBasicTests( DateType.INSTANCE, original, copy, different );
	}
