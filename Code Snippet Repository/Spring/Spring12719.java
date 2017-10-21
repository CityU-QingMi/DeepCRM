	@Test
	public void testNewlineInRequest() throws Exception {
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setUrlDecode(false);
		Object controller = new Object();
		Map<String, Object> urlMap = new LinkedHashMap<>();
		urlMap.put("/*/baz", controller);
		handlerMapping.setUrlMap(urlMap);
		handlerMapping.setApplicationContext(new StaticApplicationContext());

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo%0a%0dbar/baz");

		HandlerExecutionChain hec = handlerMapping.getHandler(request);
		assertNotNull(hec);
		assertSame(controller, hec.getHandler());
	}
