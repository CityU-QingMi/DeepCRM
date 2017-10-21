	@Test
	public void testTrackDisallowedFields() throws Exception {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		binder.setAllowedFields("name", "age");

		String name = "Rob Harrop";
		String beanName = "foobar";

		MutablePropertyValues mpvs = new MutablePropertyValues();
		mpvs.add("name", name);
		mpvs.add("beanName", beanName);
		binder.bind(mpvs);

		assertEquals(name, testBean.getName());
		String[] disallowedFields = binder.getBindingResult().getSuppressedFields();
		assertEquals(1, disallowedFields.length);
		assertEquals("beanName", disallowedFields[0]);
	}
