	public void preFlush(PersistentCollection collection) throws HibernateException {
		if ( loadedKey == null && collection.getKey() != null ) {
			loadedKey = collection.getKey();
		}

		boolean nonMutableChange = collection.isDirty()
				&& getLoadedPersister() != null
				&& !getLoadedPersister().isMutable();
		if ( nonMutableChange ) {
			throw new HibernateException(
					"changed an immutable collection instance: " +
					MessageHelper.collectionInfoString( getLoadedPersister().getRole(), getLoadedKey() )
			);
		}

		dirty( collection );

		if ( LOG.isDebugEnabled() && collection.isDirty() && getLoadedPersister() != null ) {
			LOG.debugf(
					"Collection dirty: %s",
					MessageHelper.collectionInfoString( getLoadedPersister().getRole(), getLoadedKey() )
			);
		}

		setReached( false );
		setProcessed( false );

		setDoupdate( false );
		setDoremove( false );
		setDorecreate( false );
	}
