	@Test
	public void principal() throws Exception {
		Principal principal = () -> "Foo";
		servletRequest.setUserPrincipal(principal);

		MethodParameter principalParameter = new MethodParameter(method, 3);
		assertTrue("Principal not supported", resolver.supportsParameter(principalParameter));

		Object result = resolver.resolveArgument(principalParameter, null, webRequest, null);
		assertSame("Invalid result", principal, result);
	}
