	@Test
	void findMethodsReturnsAllOverloadedMethodsThatAreNotShadowed() throws Exception {
		Class<?> clazz = InterfaceWithGenericDefaultMethodImpl.class;

		// Search for all foo(*) methods.
		List<Method> methods = findMethods(clazz, isFooMethod);

		// One might expect or desire that the signature for the generic foo(N)
		// default method would be "foo(java.lang.Long)" when looked up via the
		// concrete parameterized class, but it apparently is only _visible_ as
		// "foo(java.lang.Number)" via reflection.
		assertThat(signaturesOf(methods)).containsExactly("foo(java.lang.Number)", "foo(java.lang.Double)");
	}
