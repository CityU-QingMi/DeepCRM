	@Test
	public void createDateTimeFormatterInOrderOfPropertyPriority() throws Exception {
		factory.setStyle("SS");
		String value = applyLocale(factory.createDateTimeFormatter()).print(dateTime);
		assertTrue(value.startsWith("10/21/09"));
		assertTrue(value.endsWith("12:10 PM"));

		factory.setIso(ISO.DATE);
		assertThat(applyLocale(factory.createDateTimeFormatter()).print(dateTime), is("2009-10-21"));

		factory.setPattern("yyyyMMddHHmmss");
		assertThat(factory.createDateTimeFormatter().print(dateTime), is("20091021121000"));
	}
