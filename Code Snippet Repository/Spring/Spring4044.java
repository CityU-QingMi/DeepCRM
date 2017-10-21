	@Test
	public void testCustomFormatterForPrimitiveProperty() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb, "tb");

		binder.addCustomFormatter(new Formatter<Integer>() {
			@Override
			public Integer parse(String text, Locale locale) throws ParseException {
				return 99;
			}
			@Override
			public String print(Integer object, Locale locale) {
				return "argh";
			}
		}, "age");

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("age", "x");
		binder.bind(pvs);

		assertEquals("argh", binder.getBindingResult().getFieldValue("age"));
		assertEquals(99, tb.getAge());
	}
