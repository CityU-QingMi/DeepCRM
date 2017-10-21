	@Test
	public void testCustomEditorForPrimitiveProperty() {
		TestBean tb = new TestBean();
		DataBinder binder = new DataBinder(tb, "tb");

		binder.registerCustomEditor(int.class, "age", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(new Integer(99));
			}
			@Override
			public String getAsText() {
				return "argh";
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("age", "");
		binder.bind(pvs);

		assertEquals("argh", binder.getBindingResult().getFieldValue("age"));
		assertEquals(99, tb.getAge());
	}
