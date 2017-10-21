	@Test
	public void constructorWithAttributeNames() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);

		this.servletRequest.setSession(new MockHttpSession(null, "123"));
		this.servletRequest.getSession().setAttribute("foo", "bar");
		this.servletRequest.getSession().setAttribute("bar", "baz");

		Set<String> names = Collections.singleton("foo");
		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor(names);
		interceptor.beforeHandshake(this.request, this.response, wsHandler, attributes);

		assertEquals(2, attributes.size());
		assertEquals("bar", attributes.get("foo"));
		assertEquals("123", attributes.get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME));
	}
