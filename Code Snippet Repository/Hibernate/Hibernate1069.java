	protected CtClass loadCtClassFromClass(Class<?> aClass) {
		String resourceName = aClass.getName().replace( '.', '/' ) + ".class";
		InputStream resourceAsStream = aClass.getClassLoader().getResourceAsStream( resourceName );
		if ( resourceAsStream == null ) {
			throw new UncheckedIOException( new FileNotFoundException ( "Not found: " + resourceName ) );
		}
		try {
			return classPool.makeClass( resourceAsStream );
		}
		catch (IOException e) {
			throw new EnhancementException( "Could not prepare Javassist ClassPool", e );
		}
		finally {
			try {
				resourceAsStream.close();
			}
			catch (IOException ioe) {
				log.debugf( "An error occurs closing InputStream for class [%s]", aClass.getName() );
			}
		}
	}
