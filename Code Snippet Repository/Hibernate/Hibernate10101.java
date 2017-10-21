	public void entityChanged(Session session, Object currentRevisionData, AuditWorkUnit vwu) {
		Serializable entityId = vwu.getEntityId();
		if ( entityId instanceof PersistentCollectionChangeWorkUnit.PersistentCollectionChangeWorkUnitId ) {
			// Notify about a change in collection owner entity.
			entityId = ( (PersistentCollectionChangeWorkUnit.PersistentCollectionChangeWorkUnitId) entityId ).getOwnerId();
		}
		final Class entityClass = EntityTools.getEntityClass( sessionImplementor, vwu.getEntityName() );
		revisionInfoGenerator.entityChanged(
				entityClass,
				vwu.getEntityName(),
				entityId,
				vwu.getRevisionType(),
				currentRevisionData
		);
	}
