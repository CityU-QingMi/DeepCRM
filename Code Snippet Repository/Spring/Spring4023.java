	@Test
	public void testBindingWithCustomFormatter() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb);
		binder.addCustomFormatter(new NumberStyleFormatter(), Float.class);
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
			assertTrue(((Number) editor.getValue()).floatValue() == 1.6f);
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
