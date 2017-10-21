	@Test
	public void allowedOriginsWithSockJsService() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		String origin = "http://mydomain.com";
		registration.setAllowedOrigins(origin).withSockJS();

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());
		SockJsHttpRequestHandler requestHandler = (SockJsHttpRequestHandler)mappings.entrySet().iterator().next().getKey();
		assertNotNull(requestHandler.getSockJsService());
		DefaultSockJsService sockJsService = (DefaultSockJsService)requestHandler.getSockJsService();
		assertTrue(sockJsService.getAllowedOrigins().contains(origin));
		assertFalse(sockJsService.shouldSuppressCors());

		registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);
		registration.withSockJS().setAllowedOrigins(origin);
		mappings = registration.getMappings();
		assertEquals(1, mappings.size());
		requestHandler = (SockJsHttpRequestHandler)mappings.entrySet().iterator().next().getKey();
		assertNotNull(requestHandler.getSockJsService());
		sockJsService = (DefaultSockJsService)requestHandler.getSockJsService();
		assertTrue(sockJsService.getAllowedOrigins().contains(origin));
		assertFalse(sockJsService.shouldSuppressCors());
	}
