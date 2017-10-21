	public static File resolveClassFile(Class knownClass) {
		final String knownClassFileName = '/' + knownClass.getName().replace( '.', separatorChar ) + ".class";
		final URL knownClassFileUrl = knownClass.getResource( knownClassFileName );

		try {
			return new File( knownClassFileUrl.toURI() );
		}
		catch (URISyntaxException e) {
			throw new RuntimeException( "Could not convert class root URL to a URI", e );
		}
	}
