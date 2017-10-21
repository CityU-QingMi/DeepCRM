	public static Object createProxy(
			AuditReaderImplementor versionsReader,
			Class<?> entityClass,
			String entityName,
			Object entityId,
			Number revision,
			boolean removed,
			EnversService enversService) {
		final EntityPersister persister = versionsReader.getSessionImplementor()
				.getFactory()
				.getMetamodel()
				.entityPersister( entityName );
		return persister.createProxy(
				(Serializable) entityId,
				new ToOneDelegateSessionImplementor( versionsReader, entityClass, entityId, revision, removed, enversService )
		);
	}
