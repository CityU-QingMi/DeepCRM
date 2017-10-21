	@Test
	void invokeWithoutCustomClassLoaderDoesNotSetClassLoader() throws Exception {
		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		CustomContextClassLoaderExecutor executor = new CustomContextClassLoaderExecutor(Optional.empty());

		int result = executor.invoke(() -> {
			assertSame(originalClassLoader, Thread.currentThread().getContextClassLoader());
			return 42;
		});

		assertEquals(42, result);
		assertSame(originalClassLoader, Thread.currentThread().getContextClassLoader());
	}
