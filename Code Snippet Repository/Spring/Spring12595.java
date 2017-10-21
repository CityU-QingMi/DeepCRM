	@Test
	public void anotherLocaleRequest() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do;abc=def");
		request.addPreferredLocale(Locale.CANADA);
		request.addUserRole("role1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);

		assertTrue("Not forwarded", response.getForwardedUrl() == null);
		assertTrue(request.getAttribute("test1") != null);
		assertTrue(request.getAttribute("test1x") == null);
		assertTrue(request.getAttribute("test1y") == null);
		assertTrue(request.getAttribute("test2") != null);
		assertTrue(request.getAttribute("test2x") == null);
		assertTrue(request.getAttribute("test2y") == null);
		assertTrue(request.getAttribute("test3") != null);
		assertTrue(request.getAttribute("test3x") != null);
		assertTrue(request.getAttribute("test3y") != null);
		assertEquals("Wed, 1 Apr 2015 00:00:01 GMT", response.getHeader("Last-Modified"));
	}
