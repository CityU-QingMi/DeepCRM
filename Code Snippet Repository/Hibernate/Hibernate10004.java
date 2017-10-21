	@Override
	@SuppressWarnings({""})
	public List<PersistentCollectionChangeData> mapCollectionChanges(
			SessionImplementor session,
			String referencingPropertyName,
			PersistentCollection newColl,
			Serializable oldColl, Serializable id) {
		if ( !commonCollectionMapperData.getCollectionReferencingPropertyData().getName().equals(
				referencingPropertyName
		) ) {
			return null;
		}

		// HHH-11063
		final CollectionEntry collectionEntry = session.getPersistenceContext().getCollectionEntry( newColl );
		if ( collectionEntry != null ) {
			// This next block delegates only to the persiter-based collection change code if
			// the following are true:
			//	1. New collection is not a PersistentMap.
			//	2. The collection has a persister.
			//	3. The collection is not indexed, e.g. @IndexColumn
			//
			// In the case of 1 and 3, the collection is transformed into a set of Pair<> elements where the
			// pair's left element is either the map key or the index.  In these cases, the key/index do
			// affect the change code; hence why they're skipped here and handled at the end.
			//
			// For all others, the persister based method uses the collection's ElementType#isSame to calculate
			// equality between the newColl and oldColl.  This enforces the same equality check that core uses
			// for element types such as @Entity in cases where the hash code does not use the id field but has
			// the same value in both collections.  Using #isSame, these will be seen as differing elements and
			// changes to the collection will be returned.
			if ( !( newColl instanceof PersistentMap ) ) {
				final CollectionPersister collectionPersister = collectionEntry.getCurrentPersister();
				if ( collectionPersister != null && !collectionPersister.hasIndex() ) {
					return mapCollectionChanges( session, newColl, oldColl, id, collectionPersister );
				}
			}
		}

		return mapCollectionChanges( session, newColl, oldColl, id );
	}
