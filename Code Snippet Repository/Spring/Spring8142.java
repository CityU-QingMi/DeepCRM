	public MergedContextConfiguration(Class<?> testClass, @Nullable String[] locations, @Nullable Class<?>[] classes,
			@Nullable Set<Class<? extends ApplicationContextInitializer<?>>> contextInitializerClasses,
			@Nullable String[] activeProfiles, @Nullable String[] propertySourceLocations,
			@Nullable String[] propertySourceProperties, ContextLoader contextLoader,
			@Nullable CacheAwareContextLoaderDelegate cacheAwareContextLoaderDelegate,
			@Nullable MergedContextConfiguration parent) {

		this(testClass, locations, classes, contextInitializerClasses, activeProfiles,
				propertySourceLocations, propertySourceProperties,
				EMPTY_CONTEXT_CUSTOMIZERS, contextLoader,
				cacheAwareContextLoaderDelegate, parent);
	}
