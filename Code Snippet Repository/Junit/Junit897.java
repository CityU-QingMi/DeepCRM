	@Test
	void findMethodsDoesNotReturnOverriddenDefaultMethods() throws Exception {
		Class<?> clazz = InterfaceWithOverriddenGenericDefaultMethodImpl.class;

		// Search for all foo(*) methods.
		List<Method> methods = findMethods(clazz, isFooMethod);
		List<String> signatures = signaturesOf(methods);

		// Although the subsequent assertion covers this case as well, this
		// assertion is in place to provide a more informative failure message.
		assertThat(signatures).as("overridden default method should not be in results").doesNotContain(
			"foo(java.lang.Number)");
		assertThat(signatures).containsExactly("foo(java.lang.Long)", "foo(java.lang.Double)");
	}
