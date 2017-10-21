	@Test
	public void noCleanupAfterInclude() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/main.do");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.setAttribute("test1", "value1");
		request.setAttribute("test2", "value2");
		WebApplicationContext wac = new StaticWebApplicationContext();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
		TestBean command = new TestBean();
		request.setAttribute("command", command);

		request.setAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE, "/form.do");
		simpleDispatcherServlet.setCleanupAfterInclude(false);
		simpleDispatcherServlet.service(request, response);

		assertEquals("value1", request.getAttribute("test1"));
		assertEquals("value2", request.getAttribute("test2"));
		assertSame(wac, request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE));
	}
