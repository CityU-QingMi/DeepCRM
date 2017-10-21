		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			final Iterator<ClassLoader> clIterator = newClassLoaderIterator();
			while ( clIterator.hasNext() ) {
				final ClassLoader classLoader = clIterator.next();
				try {
					return classLoader.loadClass( name );
				}
				catch (Exception ignore) {
				}
				catch (LinkageError ignore) {
				}
			}

			throw new ClassNotFoundException( "Could not load requested class : " + name );
		}
