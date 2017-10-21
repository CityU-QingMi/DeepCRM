	@Test
	public void cleanupAfterIncludeWithRemove() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/main.do");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.setAttribute("test1", "value1");
		request.setAttribute("test2", "value2");
		WebApplicationContext wac = new StaticWebApplicationContext();
		request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

		request.setAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE, "/form.do");
		simpleDispatcherServlet.service(request, response);

		assertEquals("value1", request.getAttribute("test1"));
		assertEquals("value2", request.getAttribute("test2"));
		assertEquals(wac, request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		assertNull(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		assertNull(request.getAttribute("command"));
	}
