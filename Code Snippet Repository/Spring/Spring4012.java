	@Test
	public void nestedBindingWithDefaultConversionNoErrors() throws Exception {
		FieldAccessBean rod = new FieldAccessBean();
		DataBinder binder = new DataBinder(rod, "person");
		assertTrue(binder.isIgnoreUnknownFields());
		binder.initDirectFieldAccess();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("spouse.name", "Kerry"));
		pvs.addPropertyValue(new PropertyValue("spouse.jedi", "on"));

		binder.bind(pvs);
		binder.close();

		assertEquals("Kerry", rod.getSpouse().getName());
		assertTrue((rod.getSpouse()).isJedi());
	}
