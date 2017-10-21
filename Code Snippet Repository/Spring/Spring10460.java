	@Test
	public void testFieldDefaultWithNestedProperty() throws Exception {
		TestBean target = new TestBean();
		target.setSpouse(new TestBean());
		WebRequestDataBinder binder = new WebRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("!spouse.postProcessed", "on");
		request.addParameter("_spouse.postProcessed", "visible");
		request.addParameter("spouse.postProcessed", "on");
		binder.bind(new ServletWebRequest(request));
		assertTrue(((TestBean) target.getSpouse()).isPostProcessed());

		request.removeParameter("spouse.postProcessed");
		binder.bind(new ServletWebRequest(request));
		assertTrue(((TestBean) target.getSpouse()).isPostProcessed());

		request.removeParameter("!spouse.postProcessed");
		binder.bind(new ServletWebRequest(request));
		assertFalse(((TestBean) target.getSpouse()).isPostProcessed());
	}
