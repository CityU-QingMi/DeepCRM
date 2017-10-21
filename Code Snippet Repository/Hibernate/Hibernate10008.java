	@SuppressWarnings("")
	private List<PersistentCollectionChangeData> mapCollectionChanges(
			SessionImplementor session,
			PersistentCollection newColl,
			Serializable oldColl,
			Serializable id) {
		final List<PersistentCollectionChangeData> collectionChanges = new ArrayList<PersistentCollectionChangeData>();

		// Comparing new and old collection content.
		final Collection newCollection = getNewCollectionContent( newColl );
		final Collection oldCollection = getOldCollectionContent( oldColl );

		final Set<Object> added = new HashSet<>();
		if ( newColl != null ) {
			added.addAll( newCollection );
		}
		// Re-hashing the old collection as the hash codes of the elements there may have changed, and the
		// removeAll in AbstractSet has an implementation that is hashcode-change sensitive (as opposed to addAll).
		if ( oldColl != null ) {
			added.removeAll( new HashSet( oldCollection ) );
		}
		addCollectionChanges( session, collectionChanges, added, RevisionType.ADD, id );

		final Set<Object> deleted = new HashSet<>();
		if ( oldColl != null ) {
			deleted.addAll( oldCollection );
		}
		// The same as above - re-hashing new collection.
		if ( newColl != null ) {
			deleted.removeAll( new HashSet( newCollection ) );
		}
		addCollectionChanges( session, collectionChanges, deleted, RevisionType.DEL, id );

		return collectionChanges;
	}
