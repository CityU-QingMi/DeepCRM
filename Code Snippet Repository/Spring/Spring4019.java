	@Test
	public void testBindingWithFormatterAgainstList() {
		BeanWithIntegerList tb = new BeanWithIntegerList();
		DataBinder binder = new DataBinder(tb);
		FormattingConversionService conversionService = new FormattingConversionService();
		DefaultConversionService.addDefaultConverters(conversionService);
		conversionService.addFormatterForFieldType(Float.class, new NumberStyleFormatter());
		binder.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("integerList[0]", "1");

		LocaleContextHolder.setLocale(Locale.GERMAN);
		try {
			binder.bind(pvs);
			assertEquals(new Integer(1), tb.getIntegerList().get(0));
			assertEquals("1", binder.getBindingResult().getFieldValue("integerList[0]"));
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
