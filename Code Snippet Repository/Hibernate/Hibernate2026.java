	@Override
	public void initializeNonLazyCollections() throws HibernateException {
		if ( loadCounter == 0 ) {
			if ( TRACE_ENABLED ) {
				LOG.trace( "Initializing non-lazy collections" );
			}

			//do this work only at the very highest level of the load
			//don't let this method be called recursively
			loadCounter++;
			try {
				int size;
				while ( ( size = nonlazyCollections.size() ) > 0 ) {
					//note that each iteration of the loop may add new elements
					nonlazyCollections.remove( size - 1 ).forceInitialization();
				}
			}
			finally {
				loadCounter--;
				clearNullProperties();
			}
		}
	}
