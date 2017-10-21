	@Test
	@SuppressWarnings("")
	public void getPrincipalFromNativeSession() {
		TestPrincipal user = new TestPrincipal("joe");

		UpgradeRequest request = Mockito.mock(UpgradeRequest.class);
		given(request.getUserPrincipal()).willReturn(user);

		UpgradeResponse response = Mockito.mock(UpgradeResponse.class);
		given(response.getAcceptedSubProtocol()).willReturn(null);

		Session nativeSession = Mockito.mock(Session.class);
		given(nativeSession.getUpgradeRequest()).willReturn(request);
		given(nativeSession.getUpgradeResponse()).willReturn(response);

		JettyWebSocketSession session = new JettyWebSocketSession(attributes);
		session.initializeNativeSession(nativeSession);

		reset(nativeSession);

		assertSame(user, session.getPrincipal());
		verifyNoMoreInteractions(nativeSession);
	}
