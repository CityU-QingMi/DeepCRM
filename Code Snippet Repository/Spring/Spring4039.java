	@Test
	public void testCustomEditorWithOldValueAccess() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb, "tb");

		binder.registerCustomEditor(String.class, null, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (getValue() == null || !text.equalsIgnoreCase(getValue().toString())) {
					setValue(text);
				}
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "value");
		binder.bind(pvs);
		assertEquals("value", tb.getName());

		pvs = new MutablePropertyValues();
		pvs.add("name", "vaLue");
		binder.bind(pvs);
		assertEquals("value", tb.getName());
	}
