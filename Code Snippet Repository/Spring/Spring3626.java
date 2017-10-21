	@Test
	public void testBindLocalTimeWithSpecificFormatter() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setTimeFormatter(org.joda.time.format.DateTimeFormat.forPattern("HHmmss"));
		setUp(registrar);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("localTime", "130000");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("130000", binder.getBindingResult().getFieldValue("localTime"));
	}
