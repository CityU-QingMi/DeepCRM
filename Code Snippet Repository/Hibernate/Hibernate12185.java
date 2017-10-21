	public InputStream getInputStreamForResource(String resource) {
		// METAGEN-75
		if ( !resource.startsWith( RESOURCE_PATH_SEPARATOR ) ) {
			resource = RESOURCE_PATH_SEPARATOR + resource;
		}

		String pkg = getPackage( resource );
		String name = getRelativeName( resource );
		InputStream ormStream;
		try {
			FileObject fileObject = context.getProcessingEnvironment()
					.getFiler()
					.getResource( StandardLocation.CLASS_OUTPUT, pkg, name );
			ormStream = fileObject.openInputStream();
		}
		catch ( IOException e1 ) {
			// TODO - METAGEN-12
			// unfortunately, the Filer.getResource API seems not to be able to load from /META-INF. One gets a
			// FilerException with the message with "Illegal name /META-INF". This means that we have to revert to
			// using the classpath. This might mean that we find a persistence.xml which is 'part of another jar.
			// Not sure what else we can do here
			ormStream = this.getClass().getResourceAsStream( resource );
		}
		return ormStream;
	}
