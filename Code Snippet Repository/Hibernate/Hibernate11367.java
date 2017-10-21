	private ConfigurationBuilderHolder loadConfiguration(ServiceRegistry serviceRegistry, String configFile) {
		final FileLookup fileLookup = FileLookupFactory.newInstance();
		final ClassLoader infinispanClassLoader = InfinispanRegionFactory.class.getClassLoader();
		return serviceRegistry.getService( ClassLoaderService.class ).workWithClassLoader(
				new ClassLoaderService.Work<ConfigurationBuilderHolder>() {
					@Override
					public ConfigurationBuilderHolder doWork(ClassLoader classLoader) {
						InputStream is = null;
						try {
							is = fileLookup.lookupFile(configFile, classLoader );
							if ( is == null ) {
								// when it's not a user-provided configuration file, it might be a default configuration file,
								// and if that's included in [this] module might not be visible to the ClassLoaderService:
								classLoader = infinispanClassLoader;
								// This time use lookupFile*Strict* so to provide an exception if we can't find it yet:
								is = FileLookupFactory.newInstance().lookupFileStrict(configFile, classLoader );
							}
							final ParserRegistry parserRegistry = new ParserRegistry( infinispanClassLoader );
							final ConfigurationBuilderHolder holder = parseWithOverridenClassLoader( parserRegistry, is, infinispanClassLoader );

							return holder;
						}
						catch (IOException e) {
							throw log.unableToCreateCacheManager(e);
						}
						finally {
							Util.close( is );
						}
					}
				}
		);
	}
