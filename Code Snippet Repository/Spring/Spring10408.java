	@Test
	public void testFieldDefault() throws Exception {
		TestBean target = new TestBean();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("!postProcessed", "off");
		request.addParameter("postProcessed", "on");
		binder.bind(request);
		assertTrue(target.isPostProcessed());

		request.removeParameter("postProcessed");
		binder.bind(request);
		assertFalse(target.isPostProcessed());
	}
