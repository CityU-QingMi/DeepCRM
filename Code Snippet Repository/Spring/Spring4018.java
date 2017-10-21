	@Test
	public void testBindingErrorWithRuntimeExceptionFromFormatter() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb);
		FormattingConversionService conversionService = new FormattingConversionService();
		DefaultConversionService.addDefaultConverters(conversionService);

		conversionService.addFormatter(new Formatter<String>() {
			@Override
			public String parse(String text, Locale locale) throws ParseException {
				throw new RuntimeException(text);
			}
			@Override
			public String print(String object, Locale locale) {
				return object;
			}
		});

		binder.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "test");

		binder.bind(pvs);
		assertTrue(binder.getBindingResult().hasFieldErrors("name"));
		assertEquals("typeMismatch", binder.getBindingResult().getFieldError("name").getCode());
		assertEquals("test", binder.getBindingResult().getFieldValue("name"));
	}
