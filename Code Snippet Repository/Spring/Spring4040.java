	@Test
	public void testCustomEditorForSingleProperty() {
		TestBean tb = new TestBean();
		tb.setSpouse(new TestBean());
		DataBinder binder = new DataBinder(tb, "tb");

		binder.registerCustomEditor(String.class, "name", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue("prefix" + text);
			}
			@Override
			public String getAsText() {
				return ((String) getValue()).substring(6);
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "value");
		pvs.add("touchy", "value");
		pvs.add("spouse.name", "sue");
		binder.bind(pvs);

		binder.getBindingResult().rejectValue("name", "someCode", "someMessage");
		binder.getBindingResult().rejectValue("touchy", "someCode", "someMessage");
		binder.getBindingResult().rejectValue("spouse.name", "someCode", "someMessage");

		assertEquals("", binder.getBindingResult().getNestedPath());
		assertEquals("value", binder.getBindingResult().getFieldValue("name"));
		assertEquals("prefixvalue", binder.getBindingResult().getFieldError("name").getRejectedValue());
		assertEquals("prefixvalue", tb.getName());
		assertEquals("value", binder.getBindingResult().getFieldValue("touchy"));
		assertEquals("value", binder.getBindingResult().getFieldError("touchy").getRejectedValue());
		assertEquals("value", tb.getTouchy());

		assertTrue(binder.getBindingResult().hasFieldErrors("spouse.*"));
		assertEquals(1, binder.getBindingResult().getFieldErrorCount("spouse.*"));
		assertEquals("spouse.name", binder.getBindingResult().getFieldError("spouse.*").getField());
	}
