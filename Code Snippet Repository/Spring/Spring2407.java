	public TomcatLoadTimeWeaver(@Nullable ClassLoader classLoader) {
		Assert.notNull(classLoader, "ClassLoader must not be null");
		this.classLoader = classLoader;

		Class<?> instrumentableLoaderClass;
		try {
			instrumentableLoaderClass = classLoader.loadClass(INSTRUMENTABLE_LOADER_CLASS_NAME);
			if (!instrumentableLoaderClass.isInstance(classLoader)) {
				// Could still be a custom variant of a convention-compatible ClassLoader
				instrumentableLoaderClass = classLoader.getClass();
			}
		}
		catch (ClassNotFoundException ex) {
			// We're on an earlier version of Tomcat, probably with Spring's TomcatInstrumentableClassLoader
			instrumentableLoaderClass = classLoader.getClass();
		}

		try {
			this.addTransformerMethod = instrumentableLoaderClass.getMethod("addTransformer", ClassFileTransformer.class);
			// Check for Tomcat's new copyWithoutTransformers on InstrumentableClassLoader first
			Method copyMethod = ClassUtils.getMethodIfAvailable(instrumentableLoaderClass, "copyWithoutTransformers");
			if (copyMethod == null) {
				// Fallback: expecting TomcatInstrumentableClassLoader's getThrowawayClassLoader
				copyMethod = instrumentableLoaderClass.getMethod("getThrowawayClassLoader");
			}
			this.copyMethod = copyMethod;
		}
		catch (Throwable ex) {
			throw new IllegalStateException(
					"Could not initialize TomcatLoadTimeWeaver because Tomcat API classes are not available", ex);
		}
	}
