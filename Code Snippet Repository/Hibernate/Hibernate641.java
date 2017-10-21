	@Override
	@SuppressWarnings("")
	public Class<?> classForName(String name) {
		if ( name == null ) {
			throw new IllegalArgumentException( "Name of class to load cannot be null" );
		}

		if ( isSafeClass( name ) ) {
			return classLoaderService.classForName( name );
		}
		else {
			log.debugf( "Not known whether passed class name [%s] is safe", name );
			if ( jpaTempClassLoader == null ) {
				log.debugf(
						"No temp ClassLoader provided; using live ClassLoader " +
								"for loading potentially unsafe class : %s",
						name
				);
				return classLoaderService.classForName( name );
			}
			else {
				log.debugf(
						"Temp ClassLoader was provided, so we will use that : %s",
						name
				);
				try {
					return jpaTempClassLoader.loadClass( name );
				}
				catch (ClassNotFoundException e) {
					throw new ClassLoadingException( name );
				}
			}
		}
	}
