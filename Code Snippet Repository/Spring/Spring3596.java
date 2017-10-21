	private void doTestInvokesMethodOnEjbInstanceWithConnectExceptionWithRefresh(
			boolean lookupHomeOnStartup, boolean cacheHome) throws Exception {

		final RemoteInterface ejb = mock(RemoteInterface.class);
		given(ejb.targetMethod()).willThrow(new ConnectException(""));

		int lookupCount = 2;
		if (!cacheHome) {
			lookupCount++;
			if (lookupHomeOnStartup) {
				lookupCount++;
			}
		}

		final String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		SimpleRemoteSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);
		si.setRefreshHomeOnConnectFailure(true);
		si.setLookupHomeOnStartup(lookupHomeOnStartup);
		si.setCacheHome(cacheHome);

		RemoteInterface target = (RemoteInterface) configuredProxy(si, RemoteInterface.class);
		try {
			target.targetMethod();
			fail("Should have thrown RemoteException");
		}
		catch (ConnectException ex) {
			// expected
		}

		verify(mockContext, times(lookupCount)).close();
		verify(ejb, times(2)).remove();
	}
