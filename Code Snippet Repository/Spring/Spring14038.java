	@Test
	@SuppressWarnings("")
	public void getAcceptedProtocol() {
		String protocol = "foo";

		Session nativeSession = Mockito.mock(Session.class);
		given(nativeSession.getNegotiatedSubprotocol()).willReturn(protocol);

		StandardWebSocketSession session = new StandardWebSocketSession(this.headers, this.attributes, null, null);
		session.initializeNativeSession(nativeSession);

		reset(nativeSession);

		assertEquals(protocol, session.getAcceptedProtocol());
		verifyNoMoreInteractions(nativeSession);
	}
