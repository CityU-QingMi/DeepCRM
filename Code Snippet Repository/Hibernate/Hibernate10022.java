	@Override
	protected Object queryForReferencedEntity(
			AuditReaderImplementor versionsReader,
			EntityInfo referencedEntity,
			Serializable primaryKey,
			Number revision) {
		return versionsReader.createQuery().forEntitiesAtRevision(
				referencedEntity.getEntityClass(),
				referencedEntity.getEntityName(), revision
		)
				.add( AuditEntity.relatedId( owningReferencePropertyName ).eq( primaryKey ) )
				.getSingleResult();
	}
