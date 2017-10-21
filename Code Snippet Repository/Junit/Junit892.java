	@Test
	void getDefaultClassLoaderWithNullContextClassLoader() {
		ClassLoader original = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(null);
		try {
			assertSame(ClassLoader.getSystemClassLoader(), ClassLoaderUtils.getDefaultClassLoader());
		}
		finally {
			Thread.currentThread().setContextClassLoader(original);
		}
	}
