	@Test
	public void testBindingWithDefaultConversionNoErrors() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		assertTrue(binder.isIgnoreUnknownFields());
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "Rod");
		pvs.add("jedi", "on");

		binder.bind(pvs);
		binder.close();

		assertEquals("Rod", rod.getName());
		assertTrue(rod.isJedi());
	}
