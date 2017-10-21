	void assertMergedConfig(
			MergedContextConfiguration mergedConfig,
			Class<?> expectedTestClass,
			String[] expectedLocations,
			Class<?>[] expectedClasses,
			Set<Class<? extends ApplicationContextInitializer<?>>> expectedInitializerClasses,
			Class<? extends ContextLoader> expectedContextLoaderClass) {

		assertNotNull(mergedConfig);
		assertEquals(expectedTestClass, mergedConfig.getTestClass());
		assertNotNull(mergedConfig.getLocations());
		assertArrayEquals(expectedLocations, mergedConfig.getLocations());
		assertNotNull(mergedConfig.getClasses());
		assertArrayEquals(expectedClasses, mergedConfig.getClasses());
		assertNotNull(mergedConfig.getActiveProfiles());
		if (expectedContextLoaderClass == null) {
			assertNull(mergedConfig.getContextLoader());
		}
		else {
			assertEquals(expectedContextLoaderClass, mergedConfig.getContextLoader().getClass());
		}
		assertNotNull(mergedConfig.getContextInitializerClasses());
		assertEquals(expectedInitializerClasses, mergedConfig.getContextInitializerClasses());
	}
