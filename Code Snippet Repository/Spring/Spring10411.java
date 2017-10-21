	@Test
	public void testWithCommaSeparatedStringArray() throws Exception {
		TestBean target = new TestBean();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(target);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("stringArray", "bar");
		request.addParameter("stringArray", "abc");
		request.addParameter("stringArray", "123,def");
		binder.bind(request);
		assertEquals("Expected all three items to be bound", 3, target.getStringArray().length);

		request.removeParameter("stringArray");
		request.addParameter("stringArray", "123,def");
		binder.bind(request);
		assertEquals("Expected only 1 item to be bound", 1, target.getStringArray().length);
	}
