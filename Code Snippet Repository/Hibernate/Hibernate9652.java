		public Class loadClass(String name) throws ClassNotFoundException {
			if ( name.equals( "org.hibernate.LockMode" ) ) {
				throw new ClassNotFoundException( "Could not find "+ name );
			}
			if ( ! name.equals( "org.hibernate.test.util.SerializableThing" ) ) {
				return getParent().loadClass( name );
			}

			Class c = findLoadedClass( name );
			if ( c != null ) {
				return c;
			}

			InputStream is = this.getResourceAsStream( name.replace( '.', '/' ) + ".class" );
			if ( is == null ) {
				throw new ClassNotFoundException( name + " not found" );
			}

			try {
				byte[] bytecode = ByteCodeHelper.readByteCode( is );
				return defineClass( name, bytecode, 0, bytecode.length );
			}
			catch( Throwable t ) {
				throw new ClassNotFoundException( name + " not found", t );
			}
		}
