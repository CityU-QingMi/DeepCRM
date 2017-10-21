	@Test
	public void testBindLocalDateWithSpecificFormatter() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setDateFormatter(org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd"));
		setUp(registrar);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("localDate", "20091031");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("20091031", binder.getBindingResult().getFieldValue("localDate"));
	}
