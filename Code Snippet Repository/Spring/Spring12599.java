	@Test
	public void handlerInterceptorAbort() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do");
		request.addParameter("abort", "true");
		request.addPreferredLocale(Locale.CANADA);
		request.addUserRole("role1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertTrue("Not forwarded", response.getForwardedUrl() == null);
		assertTrue(request.getAttribute("test1") != null);
		assertTrue(request.getAttribute("test1x") != null);
		assertTrue(request.getAttribute("test1y") == null);
		assertTrue(request.getAttribute("test2") == null);
		assertTrue(request.getAttribute("test2x") == null);
		assertTrue(request.getAttribute("test2y") == null);
	}
