	protected Object readElementByIndex(final Object index) {
		if ( !initialized ) {
			class ExtraLazyElementByIndexReader implements LazyInitializationWork {
				private boolean isExtraLazy;
				private Object element;

				@Override
				public Object doWork() {
					final CollectionEntry entry = session.getPersistenceContext().getCollectionEntry( AbstractPersistentCollection.this );
					final CollectionPersister persister = entry.getLoadedPersister();
					isExtraLazy = persister.isExtraLazy();
					if ( isExtraLazy ) {
						if ( hasQueuedOperations() ) {
							session.flush();
						}
						element = persister.getElementByIndex( entry.getLoadedKey(), index, session, owner );
					}
					else {
						read();
					}
					return null;
				}
			}

			final ExtraLazyElementByIndexReader reader = new ExtraLazyElementByIndexReader();
			//noinspection unchecked
			withTemporarySessionIfNeeded( reader );
			if ( reader.isExtraLazy ) {
				return reader.element;
			}
		}
		return UNKNOWN;

	}
