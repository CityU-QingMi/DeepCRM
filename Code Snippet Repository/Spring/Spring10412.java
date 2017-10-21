	@Test
	public void testBindingWithNestedObjectCreationAndWrongOrder() throws Exception {
		TestBean tb = new TestBean();

		ServletRequestDataBinder binder = new ServletRequestDataBinder(tb, "person");
		binder.registerCustomEditor(ITestBean.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(new TestBean());
			}
		});

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("spouse.name", "test");
		request.addParameter("spouse", "someValue");
		binder.bind(request);

		assertNotNull(tb.getSpouse());
		assertEquals("test", tb.getSpouse().getName());
	}
