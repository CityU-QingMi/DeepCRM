	public WebSphereClassLoaderAdapter(ClassLoader classLoader) {
		Class<?> wsCompoundClassLoaderClass;
		try {
			wsCompoundClassLoaderClass = classLoader.loadClass(COMPOUND_CLASS_LOADER_NAME);
			this.cloneConstructor = classLoader.getClass().getDeclaredConstructor(wsCompoundClassLoaderClass);
			this.cloneConstructor.setAccessible(true);

			this.wsPreProcessorClass = classLoader.loadClass(CLASS_PRE_PROCESSOR_NAME);
			this.addPreDefinePlugin = classLoader.getClass().getMethod("addPreDefinePlugin", this.wsPreProcessorClass);
			this.transformerList = wsCompoundClassLoaderClass.getDeclaredField(PLUGINS_FIELD);
			this.transformerList.setAccessible(true);
		}
		catch (Throwable ex) {
			throw new IllegalStateException(
					"Could not initialize WebSphere LoadTimeWeaver because WebSphere API classes are not available", ex);
		}

		if (!wsCompoundClassLoaderClass.isInstance(classLoader)) {
			throw new IllegalArgumentException("ClassLoader must be instance of " + COMPOUND_CLASS_LOADER_NAME);
		}
		this.classLoader = classLoader;
	}
