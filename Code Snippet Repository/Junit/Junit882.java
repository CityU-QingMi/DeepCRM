	@Test
	void getDeclaredConstructor() {
		Constructor<?> constructor = ReflectionUtils.getDeclaredConstructor(getClass());
		assertNotNull(constructor);
		assertEquals(getClass(), constructor.getDeclaringClass());

		constructor = ReflectionUtils.getDeclaredConstructor(ClassWithOneCustomConstructor.class);
		assertNotNull(constructor);
		assertEquals(ClassWithOneCustomConstructor.class, constructor.getDeclaringClass());
		assertEquals(String.class, constructor.getParameterTypes()[0]);
	}
