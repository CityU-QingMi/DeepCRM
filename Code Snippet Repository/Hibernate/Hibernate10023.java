	@Override
	protected Object queryForReferencedEntity(
			AuditReaderImplementor versionsReader, EntityInfo referencedEntity,
			Serializable primaryKey, Number revision) {
		if ( referencedEntity.isAudited() ) {
			// Audited relation.
			return versionsReader.createQuery().forEntitiesAtRevision(
					referencedEntity.getEntityClass(),
					referencedEntity.getEntityName(), revision
			)
					.add( AuditEntity.id().eq( primaryKey ) )
					.getSingleResult();
		}
		else {
			// Not audited relation.
			return createNotAuditedEntityReference(
					versionsReader, referencedEntity.getEntityClass(),
					referencedEntity.getEntityName(), primaryKey
			);
		}
	}
