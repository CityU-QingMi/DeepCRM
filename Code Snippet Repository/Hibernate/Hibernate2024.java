	private void addCollection(PersistentCollection coll, CollectionEntry entry, Serializable key) {
		collectionEntries.put( coll, entry );
		final CollectionKey collectionKey = new CollectionKey( entry.getLoadedPersister(), key );
		final PersistentCollection old = collectionsByKey.put( collectionKey, coll );
		if ( old != null ) {
			if ( old == coll ) {
				throw new AssertionFailure( "bug adding collection twice" );
			}
			// or should it actually throw an exception?
			old.unsetSession( session );
			collectionEntries.remove( old );
			// watch out for a case where old is still referenced
			// somewhere in the object graph! (which is a user error)
		}
	}
