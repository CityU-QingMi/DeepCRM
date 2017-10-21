	private void addCollectionChanges(
			SessionImplementor session, List<PersistentCollectionChangeData> collectionChanges,
			Set<Object> changed, RevisionType revisionType, Serializable id) {
		int ordinal = 0;

		for ( Object changedObj : changed ) {
			final Map<String, Object> entityData = new HashMap<>();
			final Map<String, Object> originalId = createIdMap( ordinal++ );
			entityData.put( commonCollectionMapperData.getVerEntCfg().getOriginalIdPropName(), originalId );

			collectionChanges.add(
					new PersistentCollectionChangeData(
							commonCollectionMapperData.getVersionsMiddleEntityName(), entityData, changedObj
					)
			);
			// Mapping the collection owner's id.
			commonCollectionMapperData.getReferencingIdData().getPrefixedMapper().mapToMapFromId( originalId, id );

			// Mapping collection element and index (if present).
			mapToMapFromObject( session, originalId, entityData, changedObj );

			( revisionTypeInId ? originalId : entityData ).put(
					commonCollectionMapperData.getVerEntCfg()
							.getRevisionTypePropName(), revisionType
			);
		}
	}
