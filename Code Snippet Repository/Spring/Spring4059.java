	@Test
	public void testBindingNoErrorsWithInvalidField() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "Rod");
		pvs.add("spouse.age", 32);

		try {
			binder.bind(pvs);
			fail("Should have thrown NullValueInNestedPathException");
		}
		catch (NullValueInNestedPathException ex) {
			// expected
		}
	}
