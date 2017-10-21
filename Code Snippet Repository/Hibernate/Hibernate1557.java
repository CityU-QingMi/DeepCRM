	protected Boolean readElementExistence(final Object element) {
		if ( !initialized ) {
			final Boolean extraLazyExistenceCheck = withTemporarySessionIfNeeded(
					new LazyInitializationWork<Boolean>() {
						@Override
						public Boolean doWork() {
							final CollectionEntry entry = session.getPersistenceContext().getCollectionEntry( AbstractPersistentCollection.this );
							final CollectionPersister persister = entry.getLoadedPersister();
							if ( persister.isExtraLazy() ) {
								if ( hasQueuedOperations() ) {
									session.flush();
								}
								return persister.elementExists( entry.getLoadedKey(), element, session );
							}
							else {
								read();
							}
							return null;
						}
					}
			);
			if ( extraLazyExistenceCheck != null ) {
				return extraLazyExistenceCheck;
			}
		}
		return null;
	}
