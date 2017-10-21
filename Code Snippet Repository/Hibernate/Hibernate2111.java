	public void validate() {
		final int size = size();

		if ( !primed && size >= minSize ) {
			// IMPL NOTE : the purpose of primed is to allow the pool to lazily reach its
			// defined min-size.
			log.debug( "Connection pool now considered primed; min-size will be maintained" );
			primed = true;
		}

		if ( size < minSize && primed ) {
			int numberToBeAdded = minSize - size;
			log.debugf( "Adding %s Connections to the pool", numberToBeAdded );
			addConnections( numberToBeAdded );
		}
		else if ( size > maxSize ) {
			int numberToBeRemoved = size - maxSize;
			log.debugf( "Removing %s Connections from the pool", numberToBeRemoved );
			removeConnections( numberToBeRemoved );
		}
	}
