	@Test
	public void testBindingWithSystemFieldError() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("class.classLoader.URLs[0]", "http://myserver");
		binder.setIgnoreUnknownFields(false);

		try {
			binder.bind(pvs);
			fail("Should have thrown NotWritablePropertyException");
		}
		catch (NotWritablePropertyException ex) {
			assertTrue(ex.getMessage().contains("classLoader"));
		}
	}
