	@Test
	public void testSetTimeZone() {
		LocaleContextHolder.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertNull(LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+1"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setTimeZone(TimeZone.getTimeZone("GMT+2"));
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), LocaleContextHolder.getTimeZone());
		assertTrue(LocaleContextHolder.getLocaleContext() instanceof TimeZoneAwareLocaleContext);
		assertNull(LocaleContextHolder.getLocaleContext().getLocale());
		assertEquals(TimeZone.getTimeZone("GMT+2"), ((TimeZoneAwareLocaleContext) LocaleContextHolder.getLocaleContext()).getTimeZone());

		LocaleContextHolder.setTimeZone(null);
		assertNull(LocaleContextHolder.getLocaleContext());
		assertEquals(Locale.getDefault(), LocaleContextHolder.getLocale());
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());

		LocaleContextHolder.setDefaultTimeZone(TimeZone.getTimeZone("GMT+1"));
		assertEquals(TimeZone.getTimeZone("GMT+1"), LocaleContextHolder.getTimeZone());
		LocaleContextHolder.setDefaultTimeZone(null);
		assertEquals(TimeZone.getDefault(), LocaleContextHolder.getTimeZone());
	}
