	@Test
	public void testBindLocalDateWithSpecificStyle() throws Exception {
		JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
		registrar.setDateStyle("L");
		setUp(registrar);
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add("localDate", "October 31, 2009");
		binder.bind(propertyValues);
		assertEquals(0, binder.getBindingResult().getErrorCount());
		assertEquals("October 31, 2009", binder.getBindingResult().getFieldValue("localDate"));
	}
