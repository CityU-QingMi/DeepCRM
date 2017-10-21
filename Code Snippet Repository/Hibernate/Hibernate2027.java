	@Override
	public CollectionEntry getCollectionEntryOrNull(Object collection) {
		PersistentCollection coll;
		if ( collection instanceof PersistentCollection ) {
			coll = (PersistentCollection) collection;
			//if (collection==null) throw new TransientObjectException("Collection was not yet persistent");
		}
		else {
			coll = getCollectionHolder( collection );
			if ( coll == null ) {
				//it might be an unwrapped collection reference!
				//try to find a wrapper (slowish)
				final Iterator<PersistentCollection> wrappers = collectionEntries.keyIterator();
				while ( wrappers.hasNext() ) {
					final PersistentCollection pc = wrappers.next();
					if ( pc.isWrapper( collection ) ) {
						coll = pc;
						break;
					}
				}
			}
		}

		return (coll == null) ? null : getCollectionEntry( coll );
	}
