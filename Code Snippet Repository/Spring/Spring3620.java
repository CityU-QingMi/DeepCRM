	@Test
	public void testBindDateTimeWithSpecificFormatter() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setDateTimeFormatter(org.joda.time.format.DateTimeFormat.forPattern("yyyyMMddHHmmss"));
		setUp(registrar);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("dateTime", "20091031130000");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("20091031130000", binder.getBindingResult().getFieldValue("dateTime"));
	}
