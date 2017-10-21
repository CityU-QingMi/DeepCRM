	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		if ( name.startsWith( "java." ) || classTransformer == null ) {
			return getParent().loadClass( name );
		}

		final Class c = findLoadedClass( name );
		if ( c != null ) {
			return c;
		}

		final InputStream is = this.getResourceAsStream( name.replace( '.', '/' ) + ".class" );
		if ( is == null ) {
			throw new ClassNotFoundException( name + " not found" );
		}

		try {
			final byte[] originalBytecode = ByteCodeHelper.readByteCode( is );
			final byte[] transformedBytecode = classTransformer.transform( getParent(), name, null, null, originalBytecode );
			if ( transformedBytecode == null ) {
				// no transformations took place, so handle it as we would a
				// non-instrumented class
				return getParent().loadClass( name );
			}
			else {
				return defineClass( name, transformedBytecode, 0, transformedBytecode.length );
			}
		}
		catch ( Throwable t ) {
			throw new ClassNotFoundException( name + " not found", t );
		}
	}
