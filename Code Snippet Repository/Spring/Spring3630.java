	@Test
	public void testBindDateTimeISO() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setUseIsoFormat(true);
		setUp(registrar);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("dateTime", "2009-10-31T12:00:00.000Z");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("2009-10-31T07:00:00.000-05:00", binder.getBindingResult().getFieldValue("dateTime"));
	}
