	@Test
	public void stringToDateWithGlobalFormat() throws Exception {
		// SPR-10105
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		DateTimeFormatterFactory factory = new DateTimeFormatterFactory();
		factory.setIso(ISO.DATE_TIME);
		registrar.setDateTimeFormatter(factory.createDateTimeFormatter());
		setUp(registrar);
		// This is a format that cannot be parsed by new Date(String)
		String string = "2009-10-31T07:00:00.000-05:00";
		Date date = this.conversionService.convert(string, Date.class);
		assertNotNull(date);
	}
