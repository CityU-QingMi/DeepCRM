	@Test
	public void enableWithServletName() throws Exception {
		configurer.enable("defaultServlet");
		SimpleUrlHandlerMapping handlerMapping = configurer.buildHandlerMapping();
		DefaultServletHttpRequestHandler handler = (DefaultServletHttpRequestHandler) handlerMapping.getUrlMap().get("/**");

		assertNotNull(handler);
		assertEquals(Integer.MAX_VALUE, handlerMapping.getOrder());

		handler.handleRequest(new MockHttpServletRequest(), response);

		String expected = "defaultServlet";
		assertEquals("The ServletContext was not called with the default servlet name", expected, servletContext.url);
		assertEquals("The request was not forwarded", expected, response.getForwardedUrl());
	}
