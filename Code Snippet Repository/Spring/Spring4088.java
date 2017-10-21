	@Test
	public void testBindingWithFormatter() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb);
		FormattingConversionService conversionService = new FormattingConversionService();
		DefaultConversionService.addDefaultConverters(conversionService);
		conversionService.addFormatterForFieldType(Float.class, new NumberStyleFormatter());
		binder.setConversionService(conversionService);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("myFloat", "1,2");

		LocaleContextHolder.setLocale(Locale.GERMAN);
		try {
			binder.bind(pvs);
			assertEquals(new Float(1.2), tb.getMyFloat());
			assertEquals("1,2", binder.getBindingResult().getFieldValue("myFloat"));

			PropertyEditor editor = binder.getBindingResult().findEditor("myFloat", Float.class);
			assertNotNull(editor);
			editor.setValue(new Float(1.4));
			assertEquals("1,4", editor.getAsText());

			editor = binder.getBindingResult().findEditor("myFloat", null);
			assertNotNull(editor);
			editor.setAsText("1,6");
			assertEquals(new Float(1.6), editor.getValue());
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
