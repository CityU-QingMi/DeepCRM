	@Test
	public void bindingNoErrors() throws Exception {
		FieldAccessBean rod = new FieldAccessBean();
		DataBinder binder = new DataBinder(rod, "person");
		assertTrue(binder.isIgnoreUnknownFields());
		binder.initDirectFieldAccess();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("name", "Rod"));
		pvs.addPropertyValue(new PropertyValue("age", new Integer(32)));
		pvs.addPropertyValue(new PropertyValue("nonExisting", "someValue"));

		binder.bind(pvs);
		binder.close();

		assertTrue("changed name correctly", rod.getName().equals("Rod"));
		assertTrue("changed age correctly", rod.getAge() == 32);

		Map<?, ?> m = binder.getBindingResult().getModel();
		assertTrue("There is one element in map", m.size() == 2);
		FieldAccessBean tb = (FieldAccessBean) m.get("person");
		assertTrue("Same object", tb.equals(rod));
	}
