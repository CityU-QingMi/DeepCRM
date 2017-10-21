	@Override
	@SuppressWarnings("")
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		if ( classCache.containsKey( name ) ) {
			return classCache.get( name );
		}
		
		for ( Bundle bundle : bundles ) {
			try {
				final Class clazz = bundle.loadClass( name );
				if ( clazz != null ) {
					classCache.put( name, clazz );
					return clazz;
				}
			}
			catch ( Exception ignore ) {
			}
		}
		
		for ( ClassLoader classLoader : classLoaders ) {
			try {
				final Class clazz = classLoader.loadClass( name );
				if ( clazz != null ) {
					classCache.put( name, clazz );
					return clazz;
				}
			}
			catch ( Exception ignore ) {
			}
		}

		throw new ClassNotFoundException( "Could not load requested class : " + name );
	}
