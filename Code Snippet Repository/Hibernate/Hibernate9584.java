	@Test
	public void testDates() {
		final long now = System.currentTimeMillis();
		final java.util.Date original = new java.util.Date( now );
		final java.util.Date copy = new java.util.Date( now );
		final java.util.Date different = new java.util.Date( now + 9999 );
		final java.util.Date different2 = new java.util.Date( now + ( 1000L * 60L * 60L * 24L * 365L ) );

		runBasicTests( TimeType.INSTANCE, original, copy, different );
		runBasicTests( TimestampType.INSTANCE, original, copy, different );
		runBasicTests( DateType.INSTANCE, original, copy, different2 );
	}
