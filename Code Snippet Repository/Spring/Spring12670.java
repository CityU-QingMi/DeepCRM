	@Test
	public void contentNegotiation() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo.json");
		NativeWebRequest webRequest = new ServletWebRequest(request);

		RequestMappingHandlerMapping mapping = this.config.requestMappingHandlerMapping();
		ContentNegotiationManager manager = mapping.getContentNegotiationManager();
		assertEquals(Collections.singletonList(APPLICATION_JSON), manager.resolveMediaTypes(webRequest));

		request.setRequestURI("/foo.xml");
		assertEquals(Collections.singletonList(APPLICATION_XML), manager.resolveMediaTypes(webRequest));

		request.setRequestURI("/foo.rss");
		assertEquals(Collections.singletonList(MediaType.valueOf("application/rss+xml")),
				manager.resolveMediaTypes(webRequest));

		request.setRequestURI("/foo.atom");
		assertEquals(Collections.singletonList(APPLICATION_ATOM_XML), manager.resolveMediaTypes(webRequest));

		request.setRequestURI("/foo");
		request.setParameter("f", "json");
		assertEquals(Collections.singletonList(APPLICATION_JSON), manager.resolveMediaTypes(webRequest));

		request.setRequestURI("/resources/foo.gif");
		SimpleUrlHandlerMapping handlerMapping = (SimpleUrlHandlerMapping) this.config.resourceHandlerMapping();
		handlerMapping.setApplicationContext(this.context);
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		assertNotNull(chain);
		ResourceHttpRequestHandler handler = (ResourceHttpRequestHandler) chain.getHandler();
		assertNotNull(handler);
		assertSame(manager, handler.getContentNegotiationManager());
	}
