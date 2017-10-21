	public static <T> T instantiateClass(Class<T> clazz) throws BeanInstantiationException {
		Assert.notNull(clazz, "Class must not be null");
		if (clazz.isInterface()) {
			throw new BeanInstantiationException(clazz, "Specified class is an interface");
		}
		try {
			Constructor<T> ctor = (KotlinDetector.isKotlinType(clazz) ?
					KotlinDelegate.findPrimaryConstructor(clazz) : clazz.getDeclaredConstructor());
			if (ctor == null) {
				throw new BeanInstantiationException(clazz, "No default constructor found");
			}
			return instantiateClass(ctor);
		}
		catch (NoSuchMethodException ex) {
			throw new BeanInstantiationException(clazz, "No default constructor found", ex);
		}
	}
