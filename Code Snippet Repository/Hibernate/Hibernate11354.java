	private static ConfigurationBuilderHolder parseWithOverridenClassLoader(ParserRegistry configurationParser, InputStream is, ClassLoader infinispanClassLoader) {
		// Infinispan requires the context ClassLoader to have full visibility on all
		// its components and eventual extension points even *during* configuration parsing.
		final Thread currentThread = Thread.currentThread();
		final ClassLoader originalContextClassLoader = currentThread.getContextClassLoader();
		try {
			currentThread.setContextClassLoader( infinispanClassLoader );
			ConfigurationBuilderHolder builderHolder = configurationParser.parse( is );
			// Workaround Infinispan's ClassLoader strategies to bend to our will:
			builderHolder.getGlobalConfigurationBuilder().classLoader( infinispanClassLoader );
			return builderHolder;
		}
		finally {
			currentThread.setContextClassLoader( originalContextClassLoader );
		}
	}
