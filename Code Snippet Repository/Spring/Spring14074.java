	@Test
	public void minimal() {
		WebSocketHandler handler = new TextWebSocketHandler();
		this.registration.addHandler(handler, "/foo", "/bar");

		List<Mapping> mappings = this.registration.getMappings();
		assertEquals(2, mappings.size());

		Mapping m1 = mappings.get(0);
		assertEquals(handler, m1.webSocketHandler);
		assertEquals("/foo", m1.path);
		assertNotNull(m1.interceptors);
		assertEquals(1, m1.interceptors.length);
		assertEquals(OriginHandshakeInterceptor.class, m1.interceptors[0].getClass());

		Mapping m2 = mappings.get(1);
		assertEquals(handler, m2.webSocketHandler);
		assertEquals("/bar", m2.path);
		assertNotNull(m2.interceptors);
		assertEquals(1, m2.interceptors.length);
		assertEquals(OriginHandshakeInterceptor.class, m2.interceptors[0].getClass());
	}
