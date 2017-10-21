	@Test
	public void pushBuilder() throws Exception {
		final PushBuilder pushBuilder = Mockito.mock(PushBuilder.class);
		servletRequest = new MockHttpServletRequest("GET", "") {
			@Override
			public PushBuilder newPushBuilder() {
				return pushBuilder;
			}
		};
		ServletWebRequest webRequest = new ServletWebRequest(servletRequest, new MockHttpServletResponse());

		MethodParameter pushBuilderParameter = new MethodParameter(method, 11);
		assertTrue("PushBuilder not supported", resolver.supportsParameter(pushBuilderParameter));

		Object result = resolver.resolveArgument(pushBuilderParameter, null, webRequest, null);
		assertSame("Invalid result", pushBuilder, result);
	}
