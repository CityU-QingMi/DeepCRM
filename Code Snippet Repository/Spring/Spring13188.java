	@Test
	public void session() throws Exception {
		MockHttpSession session = new MockHttpSession();
		servletRequest.setSession(session);

		MethodParameter sessionParameter = new MethodParameter(method, 2);
		assertTrue("Session not supported", resolver.supportsParameter(sessionParameter));

		Object result = resolver.resolveArgument(sessionParameter, mavContainer, webRequest, null);
		assertSame("Invalid result", session, result);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
	}
