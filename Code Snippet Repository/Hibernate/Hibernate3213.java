	public static Class reflectedPropertyClass(
			String className,
			String name,
			ClassLoaderService classLoaderService) throws MappingException {
		try {
			Class clazz = classLoaderService.classForName( className );
			return getter( clazz, name ).getReturnType();
		}
		catch ( ClassLoadingException e ) {
			throw new MappingException( "class " + className + " not found while looking for property: " + name, e );
		}
	}
