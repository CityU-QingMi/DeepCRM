	@Test
	public void testBindingErrorWithFormatterAgainstList() {
		BeanWithIntegerList tb = new BeanWithIntegerList();
		DataBinder binder = new DataBinder(tb);
		FormattingConversionService conversionService = new FormattingConversionService();
		DefaultConversionService.addDefaultConverters(conversionService);
		conversionService.addFormatterForFieldType(Float.class, new NumberStyleFormatter());
		binder.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("integerList[0]", "1x2");

		LocaleContextHolder.setLocale(Locale.GERMAN);
		try {
			binder.bind(pvs);
			assertTrue(tb.getIntegerList().isEmpty());
			assertEquals("1x2", binder.getBindingResult().getFieldValue("integerList[0]"));
			assertTrue(binder.getBindingResult().hasFieldErrors("integerList[0]"));
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
