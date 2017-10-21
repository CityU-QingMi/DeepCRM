	public static URL findAsResource(final String path) {
		URL url = null;

		// First, try to locate this resource through the current
		// context classloader.
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		if ( contextClassLoader != null ) {
			url = contextClassLoader.getResource( path );
		}
		if ( url != null ) {
			return url;
		}

		// Next, try to locate this resource through this class's classloader
		url = ConfigHelper.class.getClassLoader().getResource( path );
		if ( url != null ) {
			return url;
		}

		// Next, try to locate this resource through the system classloader
		url = ClassLoader.getSystemClassLoader().getResource( path );

		// Anywhere else we should look?
		return url;
	}
