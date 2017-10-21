	@Test
	public void testBindingNoErrorsWithIgnoreInvalid() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		binder.setIgnoreInvalidFields(true);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "Rod");
		pvs.add("spouse.age", 32);

		binder.bind(pvs);
	}
