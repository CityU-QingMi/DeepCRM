		@Override
		public Enumeration<URL> getResources(String name) throws IOException {
			final LinkedHashSet<URL> resourceUrls = new LinkedHashSet<URL>();
			final Iterator<ClassLoader> clIterator = newClassLoaderIterator();
			while ( clIterator.hasNext() ) {
				final ClassLoader classLoader = clIterator.next();
				final Enumeration<URL> urls = classLoader.getResources( name );
				while ( urls.hasMoreElements() ) {
					resourceUrls.add( urls.nextElement() );
				}
			}

			return new Enumeration<URL>() {
				final Iterator<URL> resourceUrlIterator = resourceUrls.iterator();

				@Override
				public boolean hasMoreElements() {
					return resourceUrlIterator.hasNext();
				}

				@Override
				public URL nextElement() {
					return resourceUrlIterator.next();
				}
			};
		}
