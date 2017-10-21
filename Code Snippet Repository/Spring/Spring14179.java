	@Test
	public void doNotCopyAttributes() throws Exception {
		Map<String, Object> attributes = new HashMap<>();
		WebSocketHandler wsHandler = Mockito.mock(WebSocketHandler.class);

		this.servletRequest.setSession(new MockHttpSession(null, "123"));
		this.servletRequest.getSession().setAttribute("foo", "bar");

		HttpSessionHandshakeInterceptor interceptor = new HttpSessionHandshakeInterceptor();
		interceptor.setCopyAllAttributes(false);
		interceptor.beforeHandshake(this.request, this.response, wsHandler, attributes);

		assertEquals(1, attributes.size());
		assertEquals("123", attributes.get(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME));
	}
