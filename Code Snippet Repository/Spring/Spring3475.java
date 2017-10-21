	@Test
	public void testSetLocaleAndSetTimeZoneMixed() {
		LocaleContextHolder.setLocale(Locale.GERMANY);
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
		assertFalse(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocaleContext().getLocale());

		LocaleContextHolder.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMANY, LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setLocale(Locale.GERMAN);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setTimeZone(null);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
		assertFalse(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocaleContext().getLocale());

		LocaleContextHolder.setTimeZone(TimeZone.getTimeZone("GMT+2"));
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertEquals(Locale.GERMAN, LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setLocale(null);
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertNull(LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setTimeZone(null);
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
		assertNull(LocaleContextHolder.getLocaleContext());
	}
