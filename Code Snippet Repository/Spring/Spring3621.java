	@Test
	public void dateToStringWithFormat() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setDateTimeFormatter(org.joda.time.format.DateTimeFormat.shortDateTime());
		setUp(registrar);
		Date date = new Date();
		Object actual = this.conversionService.convert(date, TypeDescriptor.valueOf(Date.class), TypeDescriptor.valueOf(String.class));
		String expected = JodaTimeContextHolder.getFormatter(org.joda.time.format.DateTimeFormat.shortDateTime(), Locale.US).print(new DateTime(date));
		assertEquals(expected, actual);
	}
