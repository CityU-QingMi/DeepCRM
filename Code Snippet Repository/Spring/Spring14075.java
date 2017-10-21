	@Test
	public void interceptors() {
		WebSocketHandler handler = new TextWebSocketHandler();
		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();

		this.registration.addHandler(handler, "/foo").addInterceptors(interceptor);

		List<Mapping> mappings = this.registration.getMappings();
		assertEquals(1, mappings.size());

		Mapping mapping = mappings.get(0);
		assertEquals(handler, mapping.webSocketHandler);
		assertEquals("/foo", mapping.path);
		assertNotNull(mapping.interceptors);
		assertEquals(2, mapping.interceptors.length);
		assertEquals(interceptor, mapping.interceptors[0]);
		assertEquals(OriginHandshakeInterceptor.class, mapping.interceptors[1].getClass());
	}
