	@Override
	@SuppressWarnings("")
	public <T> Class<T> classForName(String name) {
		try {
			return (Class<T>) getClass().getClassLoader().loadClass( name );
		}
		catch (ClassNotFoundException e) {
			throw new ClassLoadingException( "Could not load class by name : " + name, e );
		}
	}
