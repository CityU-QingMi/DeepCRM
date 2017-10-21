	@Test
	public void testInvokesMethodOnEjbInstanceWithSeparateBusinessMethods() throws Exception {
		Object retVal = new Object();
		LocalInterface ejb = mock(LocalInterface.class);
		given(ejb.targetMethod()).willReturn(retVal);

		String jndiName= "foobar";
		Context mockContext = mockContext(jndiName, ejb);

		LocalSlsbInvokerInterceptor si = configuredInterceptor(mockContext, jndiName);

		ProxyFactory pf = new ProxyFactory(new Class<?>[] { BusinessMethods.class } );
		pf.addAdvice(si);
		BusinessMethods target = (BusinessMethods) pf.getProxy();

		assertTrue(target.targetMethod() == retVal);

		verify(mockContext).close();
		verify(ejb).remove();
	}
