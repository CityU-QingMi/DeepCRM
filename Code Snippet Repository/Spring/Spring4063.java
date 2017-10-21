	@Test
	public void testBindToStringArrayWithComponentEditor() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb, "tb");
		binder.registerCustomEditor(String.class, "stringArray", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue("X" + text);
			}
		});
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("stringArray", new String[] {"a1", "b2"});
		binder.bind(pvs);
		assertTrue(!binder.getBindingResult().hasErrors());
		assertEquals(2, tb.getStringArray().length);
		assertEquals("Xa1", tb.getStringArray()[0]);
		assertEquals("Xb2", tb.getStringArray()[1]);
	}
