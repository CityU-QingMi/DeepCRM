	@Test
	void getDefaultClassLoaderWithExplicitContextClassLoader() {
		ClassLoader original = Thread.currentThread().getContextClassLoader();
		ClassLoader mock = mock(ClassLoader.class);
		Thread.currentThread().setContextClassLoader(mock);
		try {
			assertSame(mock, ClassLoaderUtils.getDefaultClassLoader());
		}
		finally {
			Thread.currentThread().setContextClassLoader(original);
		}
	}
