	@Override
	protected URL findResource(String name) {
		if ( resourceCache.containsKey( name ) ) {
			return resourceCache.get( name );
		}
		
		for ( Bundle bundle : bundles ) {
			try {
				final URL resource = bundle.getResource( name );
				if ( resource != null ) {
					resourceCache.put( name, resource );
					return resource;
				}
			}
			catch ( Exception ignore ) {
			}
		}
		
		for ( ClassLoader classLoader : classLoaders ) {
			try {
				final URL resource = classLoader.getResource( name );
				if ( resource != null ) {
					resourceCache.put( name, resource );
					return resource;
				}
			}
			catch ( Exception ignore ) {
			}
		}
		
		// TODO: Error?
		return null;
	}
