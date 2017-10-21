	@Test
	public void testBindingErrorWithParseExceptionFromCustomFormatter() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb);

		binder.addCustomFormatter(new Formatter<String>() {
			@Override
			public String parse(String text, Locale locale) throws ParseException {
				throw new ParseException(text, 0);
			}
			@Override
			public String print(String object, Locale locale) {
				return object;
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "test");

		binder.bind(pvs);
		assertTrue(binder.getBindingResult().hasFieldErrors("name"));
		assertEquals("test", binder.getBindingResult().getFieldValue("name"));
		assertEquals("typeMismatch", binder.getBindingResult().getFieldError("name").getCode());
	}
