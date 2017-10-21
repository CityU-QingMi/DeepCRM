	private String generateUnexpectedSessionStateMessage(SharedSessionContractImplementor session) {
		// NOTE: If this.session != null, this.session may be operating on this collection
		// (e.g., by changing this.role, this.key, or even this.session) in a different thread.

		// Grab the current role and key (it can still get changed by this.session...)
		// If this collection is connected to this.session, then this.role and this.key should
		// be consistent with the CollectionEntry in this.session (as long as this.session doesn't
		// change it). Don't access the CollectionEntry in this.session because that could result
		// in multi-threaded access to this.session.
		final String roleCurrent = role;
		final Serializable keyCurrent = key;

		final StringBuilder sb = new StringBuilder( "Collection : " );
		if ( roleCurrent != null ) {
			sb.append( MessageHelper.collectionInfoString( roleCurrent, keyCurrent ) );
		}
		else {
			final CollectionEntry ce = session.getPersistenceContext().getCollectionEntry( this );
			if ( ce != null ) {
				sb.append(
						MessageHelper.collectionInfoString(
								ce.getLoadedPersister(),
								this,
								ce.getLoadedKey(),
								session
						)
				);
			}
			else {
				sb.append( "<unknown>" );
			}
		}
		// only include the collection contents if debug logging
		if ( LOG.isDebugEnabled() ) {
			final String collectionContents = wasInitialized() ? toString() : "<uninitialized>";
			sb.append( "\nCollection contents: [" ).append( collectionContents ).append( "]" );
		}
		return sb.toString();
	}
