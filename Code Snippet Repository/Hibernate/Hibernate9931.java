	protected final void onCollectionActionInversed(
			AbstractCollectionEvent event,
			PersistentCollection newColl,
			Serializable oldColl,
			CollectionEntry collectionEntry) {
		if ( shouldGenerateRevision( event ) ) {
			final String entityName = event.getAffectedOwnerEntityName();
			final String ownerEntityName = ( (AbstractCollectionPersister) collectionEntry.getLoadedPersister() ).getOwnerEntityName();
			final String referencingPropertyName = collectionEntry.getRole().substring( ownerEntityName.length() + 1 );

			final RelationDescription rd = searchForRelationDescription( entityName, referencingPropertyName );
			if ( rd != null ) {
				if ( rd.getRelationType().equals( RelationType.TO_MANY_NOT_OWNING ) && rd.isIndexed() ) {
					onCollectionAction( event, newColl, oldColl, collectionEntry );
				}
			}
		}
	}
