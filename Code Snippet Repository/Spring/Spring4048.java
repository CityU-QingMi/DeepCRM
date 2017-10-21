	@Test
	public void testBindingNoErrorsNotIgnoreUnknown() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		binder.setIgnoreUnknownFields(false);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "Rod");
		pvs.add("age", 32);
		pvs.add("nonExisting", "someValue");

		try {
			binder.bind(pvs);
			fail("Should have thrown NotWritablePropertyException");
		}
		catch (NotWritablePropertyException ex) {
			// expected
		}
	}
