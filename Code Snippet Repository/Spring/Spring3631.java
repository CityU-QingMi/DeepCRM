	@Test
	public void createDateTimeFormatterInOrderOfPropertyPriority() throws Exception {
		factory.setStylePattern("SS");
		String value = applyLocale(factory.createDateTimeFormatter()).format(dateTime);
		assertTrue(value.startsWith("10/21/09"));
		assertTrue(value.endsWith("12:10 PM"));

		factory.setIso(ISO.DATE);
		assertThat(applyLocale(factory.createDateTimeFormatter()).format(dateTime), is("2009-10-21"));

		factory.setPattern("yyyyMMddHHmmss");
		assertThat(factory.createDateTimeFormatter().format(dateTime), is("20091021121000"));
	}
