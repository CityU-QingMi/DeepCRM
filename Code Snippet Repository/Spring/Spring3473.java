	@Test
	public void testSetLocale() {
		LocaleContextHolder.setLocale(Locale.GERMAN);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
		assertFalse(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocaleContext().getLocale());

		LocaleContextHolder.setLocale(Locale.GERMANY);
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
		assertFalse(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocaleContext().getLocale());

		LocaleContextHolder.setLocale(null);
		assertNull(LocaleContextHolder.getLocaleContext());
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());

		LocaleContextHolder.setDefaultLocale(Locale.GERMAN);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocale());
		LocaleContextHolder.setDefaultLocale(null);
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
	}
