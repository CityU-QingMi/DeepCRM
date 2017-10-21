	@Test
	public void testConversionWithInappropriateStringEditor() {
		DataBinder dataBinder = new DataBinder(null);
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		dataBinder.setConversionService(conversionService);
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

		NameBean bean = new NameBean("Fred");
		assertEquals("ConversionService should have invoked toString()", "Fred", dataBinder.convertIfNecessary(bean, String.class));
		conversionService.addConverter(new NameBeanConverter());
		assertEquals("Type converter should have been used", "[Fred]", dataBinder.convertIfNecessary(bean, String.class));
	}
