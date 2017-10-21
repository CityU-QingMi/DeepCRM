	@Test
	public void testFieldDefaultNonBoolean() throws Exception {
		TestBean target = new TestBean();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("!name", "anonymous");
		request.addParameter("name", "Scott");
		binder.bind(request);
		assertEquals("Scott", target.getName());

		request.removeParameter("name");
		binder.bind(request);
		assertEquals("anonymous", target.getName());
	}
