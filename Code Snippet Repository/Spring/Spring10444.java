	@Test
	public void testBindingWithNestedObjectCreation() throws Exception {
		TestBean tb = new TestBean();

		WebRequestDataBinder binder = new WebRequestDataBinder(tb, "person");
		binder.registerCustomEditor(ITestBean.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(new TestBean());
			}
		});

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("spouse", "someValue");
		request.addParameter("spouse.name", "test");
		binder.bind(new ServletWebRequest(request));

		assertNotNull(tb.getSpouse());
		assertEquals("test", tb.getSpouse().getName());
	}
