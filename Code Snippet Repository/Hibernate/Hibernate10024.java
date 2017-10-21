	private Object createNotAuditedEntityReference(
			AuditReaderImplementor versionsReader, Class<?> entityClass,
			String entityName, Serializable primaryKey) {
		final EntityPersister entityPersister = versionsReader.getSessionImplementor().getFactory().getMetamodel()
				.entityPersister( entityName );
		if ( entityPersister.hasProxy() ) {
			// If possible create a proxy. Returning complete object may affect performance.
			return versionsReader.getSession().load( entityClass, primaryKey );
		}
		else {
			// If proxy is not allowed (e.g. @Proxy(lazy=false)) construct the original object.
			return versionsReader.getSession().get( entityClass, primaryKey );
		}
	}
