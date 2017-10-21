	@Override
	public void nullSafeMapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		final EntityInfo referencedEntity = getEntityInfo( enversService, referencedEntityName );

		Object value;
		try {
			value = queryForReferencedEntity( versionsReader, referencedEntity, (Serializable) primaryKey, revision );
		}
		catch (NoResultException e) {
			value = null;
		}
		catch (NonUniqueResultException e) {
			throw new AuditException(
					"Many versions results for one-to-one relationship " + entityName +
							"." + getPropertyData().getBeanName() + ".", e
			);
		}

		setPropertyValue( obj, value );
	}
