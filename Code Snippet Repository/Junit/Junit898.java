	@Test
	void findMethodsIgnoresBridgeMethods() throws Exception {
		assertFalse(Modifier.isPublic(PublicChildClass.class.getSuperclass().getModifiers()));
		assertTrue(Modifier.isPublic(PublicChildClass.class.getModifiers()));
		assertTrue(PublicChildClass.class.getDeclaredMethod("method1").isBridge());
		assertTrue(PublicChildClass.class.getDeclaredMethod("method3").isBridge());

		List<Method> methods = findMethods(PublicChildClass.class, method -> true);
		List<String> signatures = signaturesOf(methods);
		assertThat(signatures).containsOnly("method1()", "method2()", "method3()", "otherMethod1()", "otherMethod2()");
		assertEquals(0, methods.stream().filter(Method::isBridge).count());
	}
