	@Test
	public void connect() throws Exception {
		HttpHeaders handshakeHeaders = new HttpHeaders();
		handshakeHeaders.setOrigin("foo");

		TransportRequest request = mock(TransportRequest.class);
		given(request.getSockJsUrlInfo()).willReturn(new SockJsUrlInfo(new URI("http://example.com")));
		given(request.getHandshakeHeaders()).willReturn(handshakeHeaders);
		given(request.getHttpRequestHeaders()).willReturn(new HttpHeaders());

		TestXhrTransport transport = new TestXhrTransport();
		WebSocketHandler handler = mock(WebSocketHandler.class);
		transport.connect(request, handler);

		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
		verify(request).getSockJsUrlInfo();
		verify(request).addTimeoutTask(captor.capture());
		verify(request).getTransportUrl();
		verify(request).getHandshakeHeaders();
		verify(request).getHttpRequestHeaders();
		verifyNoMoreInteractions(request);

		assertEquals(1, transport.actualHandshakeHeaders.size());
		assertEquals("foo", transport.actualHandshakeHeaders.getOrigin());

		assertFalse(transport.actualSession.isDisconnected());
		captor.getValue().run();
		assertTrue(transport.actualSession.isDisconnected());
	}
