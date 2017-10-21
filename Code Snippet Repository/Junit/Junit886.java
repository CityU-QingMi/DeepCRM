	@Test
	void findMethodByParameterTypesInGenericInterfaceViaParameterizedSubclass() {
		Class<?> clazz = InterfaceWithGenericDefaultMethodImpl.class;
		Optional<Method> method = findMethod(clazz, "foo", Long.class);
		assertThat(method).isNotEmpty();
		assertThat(method.get().getName()).isEqualTo("foo");

		// One might expect or desire that the signature for the generic foo(N)
		// default method would be "foo(java.lang.Long)" when looked up via the
		// concrete parameterized class, but it apparently is only _visible_ as
		// "foo(java.lang.Number)" via reflection. Hence the following assertion
		// checks for java.lang.Number instead of java.lang.Long.
		assertThat(method.get().getParameterTypes()[0]).isEqualTo(Number.class);
	}
