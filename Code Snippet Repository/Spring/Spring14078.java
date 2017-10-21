	@Test
	public void interceptorsPassedToSockJsRegistration() {
		WebSocketHandler handler = new TextWebSocketHandler();
		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();

		this.registration.addHandler(handler, "/foo")
				.addInterceptors(interceptor)
				.setAllowedOrigins("http://mydomain1.com")
				.withSockJS();

		this.registration.getSockJsServiceRegistration().setTaskScheduler(this.taskScheduler);

		List<Mapping> mappings = this.registration.getMappings();
		assertEquals(1, mappings.size());

		Mapping mapping = mappings.get(0);
		assertEquals(handler, mapping.webSocketHandler);
		assertEquals("/foo/**", mapping.path);
		assertNotNull(mapping.sockJsService);
		assertTrue(mapping.sockJsService.getAllowedOrigins().contains("http://mydomain1.com"));
		List<HandshakeInterceptor> interceptors = mapping.sockJsService.getHandshakeInterceptors();
		assertEquals(interceptor, interceptors.get(0));
		assertEquals(OriginHandshakeInterceptor.class, interceptors.get(1).getClass());
	}
