	public static Class classForName(
			String className,
			Map<String, Class<?>> properties,
			ClassLoaderService classLoaderService) {
		Class aClass = loadClass( className, classLoaderService );
		if ( aClass == null ) {
			aClass = generate( className, properties );
		}
		return aClass;
	}
