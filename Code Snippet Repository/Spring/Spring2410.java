	public WebLogicClassLoaderAdapter(ClassLoader classLoader) {
		Class<?> wlGenericClassLoaderClass;
		try {
			wlGenericClassLoaderClass = classLoader.loadClass(GENERIC_CLASS_LOADER_NAME);
			this.wlPreProcessorClass = classLoader.loadClass(CLASS_PRE_PROCESSOR_NAME);
			this.addPreProcessorMethod = classLoader.getClass().getMethod(
					"addInstanceClassPreProcessor", this.wlPreProcessorClass);
			this.getClassFinderMethod = classLoader.getClass().getMethod("getClassFinder");
			this.getParentMethod = classLoader.getClass().getMethod("getParent");
			this.wlGenericClassLoaderConstructor = wlGenericClassLoaderClass.getConstructor(
					this.getClassFinderMethod.getReturnType(), ClassLoader.class);
		}
		catch (Throwable ex) {
			throw new IllegalStateException(
					"Could not initialize WebLogic LoadTimeWeaver because WebLogic 10 API classes are not available", ex);
		}
		if (!wlGenericClassLoaderClass.isInstance(classLoader)) {
			throw new IllegalArgumentException(
					"ClassLoader must be an instance of [" + wlGenericClassLoaderClass.getName() + "]: " + classLoader);
		}
		this.classLoader = classLoader;
	}
