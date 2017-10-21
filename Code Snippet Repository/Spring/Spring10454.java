	@Test
	public void testBindingWithNestedObjectCreationThroughAutoGrow() throws Exception {
		TestBean tb = new TestBeanWithConcreteSpouse();

		WebRequestDataBinder binder = new WebRequestDataBinder(tb, "person");
		binder.setIgnoreUnknownFields(false);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("concreteSpouse.name", "test");
		binder.bind(new ServletWebRequest(request));

		assertNotNull(tb.getSpouse());
		assertEquals("test", tb.getSpouse().getName());
	}
