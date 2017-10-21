	@SuppressWarnings("")
	public static <T> Class<T> loadClass(String name, ClassLoaderService classLoaderService)
			throws ClassLoadingException {
		try {
			if ( classLoaderService != null ) {
				return classLoaderService.classForName( name );
			}
			else {
				return (Class<T>) Thread.currentThread().getContextClassLoader().loadClass( name );
			}
		}
		catch (Exception e) {
			throw new ClassLoadingException( "Unable to load class [" + name + "]", e );
		}
	}
