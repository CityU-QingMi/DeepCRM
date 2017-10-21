	@Test
	public void testBindingWithNestedObjectCreation() throws Exception {
		TestBean tb = new TestBean();

		DataBinder binder = new DataBinder(tb, "person");
		binder.registerCustomEditor(ITestBean.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(new TestBean());
			}
		});

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("spouse", "someValue");
		pvs.add("spouse.name", "test");
		binder.bind(pvs);

		assertNotNull(tb.getSpouse());
		assertEquals("test", tb.getSpouse().getName());
	}
