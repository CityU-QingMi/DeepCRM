		@Override
		protected URL findResource(String name) {
			final Iterator<ClassLoader> clIterator = newClassLoaderIterator();
			while ( clIterator.hasNext() ) {
				final ClassLoader classLoader = clIterator.next();
				final URL resource = classLoader.getResource( name );
				if ( resource != null ) {
					return resource;
				}
			}
			return super.findResource( name );
		}
