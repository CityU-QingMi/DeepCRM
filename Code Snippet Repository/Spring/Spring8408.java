	@Test
	public void hashCodeWithSameInitializers() {
		Set<Class<? extends ApplicationContextInitializer<?>>> initializerClasses1 =
				new HashSet<>();
		initializerClasses1.add(FooInitializer.class);
		initializerClasses1.add(BarInitializer.class);

		Set<Class<? extends ApplicationContextInitializer<?>>> initializerClasses2 =
				new HashSet<>();
		initializerClasses2.add(BarInitializer.class);
		initializerClasses2.add(FooInitializer.class);

		MergedContextConfiguration mergedConfig1 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, initializerClasses1, EMPTY_STRING_ARRAY, loader);
		MergedContextConfiguration mergedConfig2 = new MergedContextConfiguration(getClass(),
				EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, initializerClasses2, EMPTY_STRING_ARRAY, loader);
		assertEquals(mergedConfig1.hashCode(), mergedConfig2.hashCode());
	}
