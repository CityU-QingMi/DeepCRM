	@Test
	@SuppressWarnings("")
	public void testFactoryMethods() {
		assertEquals(stringParameter, MethodParameter.forMethodOrConstructor(method, 0));
		assertEquals(longParameter, MethodParameter.forMethodOrConstructor(method, 1));

		assertEquals(stringParameter, MethodParameter.forExecutable(method, 0));
		assertEquals(longParameter, MethodParameter.forExecutable(method, 1));

		assertEquals(stringParameter, MethodParameter.forParameter(method.getParameters()[0]));
		assertEquals(longParameter, MethodParameter.forParameter(method.getParameters()[1]));
	}
