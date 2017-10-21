	@Test
	public void defaultConstructor() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);

		this.servletRequest.setSession(new MockHttpSession(null, "123"));
		this.servletRequest.getSession().setAttribute("foo", "bar");
		this.servletRequest.getSession().setAttribute("bar", "baz");

		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();
		interceptor.beforeHandshake(this.request, this.response, wsHandler, attributes);

		assertEquals(3, attributes.size());
		assertEquals("bar", attributes.get("foo"));
		assertEquals("baz", attributes.get("bar"));
		assertEquals("123", attributes.get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME));
	}
