	@Test
	public void disableCorsWithSockJsService() {
		WebMvcStompWebSocketEndpointRegistration registration =
				new WebMvcStompWebSocketEndpointRegistration(new String[] {"/foo"}, this.handler, this.scheduler);

		registration.withSockJS().setSupressCors(true);

		MultiValueMap<HttpRequestHandler, String> mappings = registration.getMappings();
		assertEquals(1, mappings.size());
		SockJsHttpRequestHandler requestHandler = (SockJsHttpRequestHandler)mappings.entrySet().iterator().next().getKey();
		assertNotNull(requestHandler.getSockJsService());
		DefaultSockJsService sockJsService = (DefaultSockJsService)requestHandler.getSockJsService();
		assertTrue(sockJsService.shouldSuppressCors());
	}
