	@Test
	public void testNestedBindingWithDefaultConversionNoErrors() throws Exception {
		TestBean rod = new TestBean(new TestBean());
		DataBinder binder = new DataBinder(rod, "person");
		assertTrue(binder.isIgnoreUnknownFields());
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("spouse.name", "Kerry");
		pvs.add("spouse.jedi", "on");

		binder.bind(pvs);
		binder.close();

		assertEquals("Kerry", rod.getSpouse().getName());
		assertTrue(((TestBean) rod.getSpouse()).isJedi());
	}
