	private void testException(Exception expected) throws Exception {
		LocalInterfaceWithBusinessMethods ejb = mock(LocalInterfaceWithBusinessMethods.class);
		given(ejb.targetMethod()).willThrow(expected);

		String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		LocalSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);

		ProxyFactory pf = new ProxyFactory(new Class<?>[] { LocalInterfaceWithBusinessMethods.class } );
		pf.addAdvice(si);
		LocalInterfaceWithBusinessMethods target = (LocalInterfaceWithBusinessMethods) pf.getProxy();

		try {
			target.targetMethod();
			fail("Should have thrown exception");
		}
		catch (Exception thrown) {
			assertTrue(thrown == expected);
		}

		verify(mockContext).close();
	}
