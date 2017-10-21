		private Iterator<ClassLoader> newTcclAfterIterator(final ClassLoader threadContextClassLoader) {
			final ClassLoader systemClassLoader = locateSystemClassLoader();
			return new Iterator<ClassLoader>() {
				private int currentIndex = 0;
				private boolean tcCLReturned = false;
				private boolean sysCLReturned = false;
			    
				@Override
				public boolean hasNext() {
					if ( currentIndex < individualClassLoaders.length ) {
						return true;
					}
					else if ( !tcCLReturned ) {
						return true;
					}
					else if ( !sysCLReturned && systemClassLoader != null ) {
						return true;
					}
					
					return false;
				}

				@Override
				public ClassLoader next() {
					if ( currentIndex < individualClassLoaders.length ) {
						currentIndex += 1;
						return individualClassLoaders[ currentIndex - 1 ];
					}
					else if ( !tcCLReturned ) {
						tcCLReturned = true;
						return threadContextClassLoader;
					}
					else if ( !sysCLReturned && systemClassLoader != null ) {
						sysCLReturned = true;
						return systemClassLoader;
					}
					throw new IllegalStateException( "No more item" );
				}
			};
		}
