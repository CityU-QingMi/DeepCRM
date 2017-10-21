	@Test
	public void applyBeforeHandshakeWithFalseReturnValue() throws Exception {
		given(i1.beforeHandshake(request, response, wsHandler, attributes)).willReturn(true);
		given(i2.beforeHandshake(request, response, wsHandler, attributes)).willReturn(false);

		HandshakeInterceptorChain chain = new HandshakeInterceptorChain(interceptors, wsHandler);
		chain.applyBeforeHandshake(request, response, attributes);

		verify(i1).beforeHandshake(request, response, wsHandler, attributes);
		verify(i1).afterHandshake(request, response, wsHandler, null);
		verify(i2).beforeHandshake(request, response, wsHandler, attributes);
		verifyNoMoreInteractions(i1, i2, i3);
	}
