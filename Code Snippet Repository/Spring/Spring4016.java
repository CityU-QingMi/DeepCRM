	@Test
	public void testBindingErrorWithFormatter() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb);
		FormattingConversionService conversionService = new FormattingConversionService();
		DefaultConversionService.addDefaultConverters(conversionService);
		conversionService.addFormatterForFieldType(Float.class, new NumberStyleFormatter());
		binder.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("myFloat", "1x2");

		LocaleContextHolder.setLocale(Locale.GERMAN);
		try {
			binder.bind(pvs);
			assertEquals(new Float(0.0), tb.getMyFloat());
			assertEquals("1x2", binder.getBindingResult().getFieldValue("myFloat"));
			assertTrue(binder.getBindingResult().hasFieldErrors("myFloat"));
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
