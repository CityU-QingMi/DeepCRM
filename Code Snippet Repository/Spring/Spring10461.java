	@Test
	public void testFieldDefaultNonBoolean() throws Exception {
		TestBean target = new TestBean();
		WebRequestDataBinder binder = new WebRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("!name", "anonymous");
		request.addParameter("name", "Scott");
		binder.bind(new ServletWebRequest(request));
		assertEquals("Scott", target.getName());

		request.removeParameter("name");
		binder.bind(new ServletWebRequest(request));
		assertEquals("anonymous", target.getName());
	}
