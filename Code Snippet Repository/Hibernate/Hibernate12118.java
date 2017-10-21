	public static URL resolveRootUrl(Class knownClass) {
		final String knownClassFileName = '/' + knownClass.getName().replace( '.', separatorChar ) + ".class";
		final URL knownClassFileUrl = knownClass.getResource( knownClassFileName );
		final String knownClassFileUrlString = knownClassFileUrl.toExternalForm();

		// to start, strip off the class file name
		String rootUrlString = knownClassFileUrlString.substring( 0, knownClassFileUrlString.lastIndexOf( separatorChar ) );

		// then strip off each package dir
		final String packageName = knownClass.getPackage().getName();
		for ( String packageNamePart : packageName.split( "\\." ) ) {
			rootUrlString = rootUrlString.substring( 0, rootUrlString.lastIndexOf( separatorChar ) );
		}

		try {
			return new URL( rootUrlString );
		}
		catch (MalformedURLException e) {
			throw new RuntimeException( "Could not convert class base url as string to URL ref", e );
		}
	}
