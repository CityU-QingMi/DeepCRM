		@Override
		protected Class resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
			final String className = v.getName();
			LOG.tracev( "Attempting to locate class [{0}]", className );

			try {
				return Class.forName( className, false, loader1 );
			}
			catch (ClassNotFoundException e) {
				LOG.trace( "Unable to locate class using given classloader" );
			}

			if ( different( loader1, loader2 ) ) {
				try {
					return Class.forName( className, false, loader2 );
				}
				catch (ClassNotFoundException e) {
					LOG.trace( "Unable to locate class using given classloader" );
				}
			}

			if ( different( loader1, loader3 ) && different( loader2, loader3 ) ) {
				try {
					return Class.forName( className, false, loader3 );
				}
				catch (ClassNotFoundException e) {
					LOG.trace( "Unable to locate class using given classloader" );
				}
			}

			// By default delegate to normal JDK deserialization which will use the class loader
			// of the class which is calling this deserialization.
			return super.resolveClass( v );
		}
