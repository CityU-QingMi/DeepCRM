	@Test
	void invokeWithCustomClassLoaderSetsCustomAndResetsToOriginal() throws Exception {
		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		ClassLoader customClassLoader = URLClassLoader.newInstance(new URL[0]);
		CustomContextClassLoaderExecutor executor = new CustomContextClassLoaderExecutor(
			Optional.of(customClassLoader));

		int result = executor.invoke(() -> {
			assertSame(customClassLoader, Thread.currentThread().getContextClassLoader());
			return 23;
		});

		assertEquals(23, result);
		assertSame(originalClassLoader, Thread.currentThread().getContextClassLoader());
	}
