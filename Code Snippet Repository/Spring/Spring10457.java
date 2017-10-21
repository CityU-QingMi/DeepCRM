	@Test
	public void testFieldDefault() throws Exception {
		TestBean target = new TestBean();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("!postProcessed", "off");
		request.addParameter("postProcessed", "on");
		binder.bind(new ServletWebRequest(request));
		assertTrue(target.isPostProcessed());

		request.removeParameter("postProcessed");
		binder.bind(new ServletWebRequest(request));
		assertFalse(target.isPostProcessed());
	}
