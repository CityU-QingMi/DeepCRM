	@Test
	public void testDefaultServletHandlerWithOptionalAttributes() throws Exception {
		loadBeanDefinitions("mvc-config-default-servlet-optional-attrs.xml");

		HttpRequestHandlerAdapter adapter = appContext.getBean(HttpRequestHandlerAdapter.class);
		assertNotNull(adapter);

		DefaultServletHttpRequestHandler handler = appContext.getBean(DefaultServletHttpRequestHandler.class);
		assertNotNull(handler);

		SimpleUrlHandlerMapping mapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(mapping);
		assertEquals(Ordered.LOWEST_PRECEDENCE, mapping.getOrder());

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/foo.css");
		request.setMethod("GET");

		HandlerExecutionChain chain = mapping.getHandler(request);
		assertTrue(chain.getHandler() instanceof DefaultServletHttpRequestHandler);

		MockHttpServletResponse response = new MockHttpServletResponse();
		ModelAndView mv = adapter.handle(request, response, chain.getHandler());
		assertNull(mv);
	}
