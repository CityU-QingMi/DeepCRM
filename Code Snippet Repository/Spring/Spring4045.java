	@Test
	public void testCustomFormatterForAllStringProperties() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb, "tb");

		binder.addCustomFormatter(new Formatter<String>() {
			@Override
			public String parse(String text, Locale locale) throws ParseException {
				return "prefix" + text;
			}
			@Override
			public String print(String object, Locale locale) {
				return object.substring(6);
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "value");
		pvs.add("touchy", "value");
		binder.bind(pvs);

		binder.getBindingResult().rejectValue("name", "someCode", "someMessage");
		binder.getBindingResult().rejectValue("touchy", "someCode", "someMessage");

		assertEquals("value", binder.getBindingResult().getFieldValue("name"));
		assertEquals("prefixvalue", binder.getBindingResult().getFieldError("name").getRejectedValue());
		assertEquals("prefixvalue", tb.getName());
		assertEquals("value", binder.getBindingResult().getFieldValue("touchy"));
		assertEquals("prefixvalue", binder.getBindingResult().getFieldError("touchy").getRejectedValue());
		assertEquals("prefixvalue", tb.getTouchy());
	}
