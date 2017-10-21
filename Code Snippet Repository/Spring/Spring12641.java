	@Test
	public void enable() throws Exception {
		configurer.enable();
		SimpleUrlHandlerMapping getHandlerMapping = configurer.buildHandlerMapping();
		SimpleUrlHandlerMapping handlerMapping = configurer.buildHandlerMapping();
		DefaultServletHttpRequestHandler handler = (DefaultServletHttpRequestHandler) handlerMapping.getUrlMap().get("/**");

		assertNotNull(handler);
		assertEquals(Integer.MAX_VALUE, handlerMapping.getOrder());

		handler.handleRequest(new MockHttpServletRequest(), response);

		String expected = "default";
		assertEquals("The ServletContext was not called with the default servlet name", expected, servletContext.url);
		assertEquals("The request was not forwarded", expected, response.getForwardedUrl());
	}
