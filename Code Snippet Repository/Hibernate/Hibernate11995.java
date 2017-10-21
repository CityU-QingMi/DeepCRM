	private static ClassLoader getEnhancerClassLoader(EnhancementContext context, String packageName) {
		return new ClassLoader() {

			private final String debugOutputDir = System.getProperty( "java.io.tmpdir" );

			private final Enhancer enhancer = Environment.getBytecodeProvider().getEnhancer( context );

			@SuppressWarnings( "ResultOfMethodCallIgnored" )
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				if ( !name.startsWith( packageName ) ) {
					return getParent().loadClass( name );
				}
				Class c = findLoadedClass( name );
				if ( c != null ) {
					return c;
				}

				try ( InputStream is = getResourceAsStream( name.replace( '.', '/' ) + ".class" ) ) {
					if ( is == null ) {
						throw new ClassNotFoundException( name + " not found" );
					}

					byte[] original = new byte[is.available()];
					try ( BufferedInputStream bis = new BufferedInputStream( is ) ) {
						bis.read( original );
					}

					byte[] enhanced = enhancer.enhance( name, original );
					if ( enhanced == null ) {
						return defineClass( name, original, 0, original.length );
					}

					File f = new File( debugOutputDir + File.separator + name.replace( ".", File.separator ) + ".class" );
					f.getParentFile().mkdirs();
					f.createNewFile();
					try ( FileOutputStream out = new FileOutputStream( f ) ) {
						out.write( enhanced );
					}
					return defineClass( name, enhanced, 0, enhanced.length );
				}
				catch ( Throwable t ) {
					throw new ClassNotFoundException( name + " not found", t );
				}
			}
		};
	}
