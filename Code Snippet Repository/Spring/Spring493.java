	public static void clearClassLoader(@Nullable ClassLoader classLoader) {
		for (Iterator<ClassLoader> it = acceptedClassLoaders.iterator(); it.hasNext();) {
			ClassLoader registeredLoader = it.next();
			if (isUnderneathClassLoader(registeredLoader, classLoader)) {
				it.remove();
			}
		}
		for (Iterator<Class<?>> it = strongClassCache.keySet().iterator(); it.hasNext();) {
			Class<?> beanClass = it.next();
			if (isUnderneathClassLoader(beanClass.getClassLoader(), classLoader)) {
				it.remove();
			}
		}
		for (Iterator<Class<?>> it = softClassCache.keySet().iterator(); it.hasNext();) {
			Class<?> beanClass = it.next();
			if (isUnderneathClassLoader(beanClass.getClassLoader(), classLoader)) {
				it.remove();
			}
		}
	}
