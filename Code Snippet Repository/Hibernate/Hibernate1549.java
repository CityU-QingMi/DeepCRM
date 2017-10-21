	@SuppressWarnings({""})
	protected boolean readSize() {
		if ( !initialized ) {
			if ( cachedSize != -1 && !hasQueuedOperations() ) {
				return true;
			}
			else {
				final boolean isExtraLazy = withTemporarySessionIfNeeded(
						new LazyInitializationWork<Boolean>() {
							@Override
							public Boolean doWork() {
								final CollectionEntry entry = session.getPersistenceContext().getCollectionEntry( AbstractPersistentCollection.this );

								if ( entry != null ) {
									final CollectionPersister persister = entry.getLoadedPersister();
									if ( persister.isExtraLazy() ) {
										if ( hasQueuedOperations() ) {
											session.flush();
										}
										cachedSize = persister.getSize( entry.getLoadedKey(), session );
										return true;
									}
									else {
										read();
									}
								}
								else{
									throwLazyInitializationExceptionIfNotConnected();
								}
								return false;
							}
						}
				);
				if ( isExtraLazy ) {
					return true;
				}
			}
		}
		return false;
	}
