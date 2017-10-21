	@SuppressWarnings({""})
	private void replaceNonAuditIdProxies(Map versionsEntity, Number revision) {
		final Map originalId = (Map) versionsEntity.get( enversService.getAuditEntitiesConfiguration().getOriginalIdPropName() );
		for ( Object key : originalId.keySet() ) {
			final Object value = originalId.get( key );
			if ( value instanceof HibernateProxy ) {
				final HibernateProxy hibernateProxy = (HibernateProxy) value;
				final LazyInitializer initializer = hibernateProxy.getHibernateLazyInitializer();
				final String entityName = initializer.getEntityName();
				final Serializable entityId = initializer.getIdentifier();
				if ( enversService.getEntitiesConfigurations().isVersioned( entityName ) ) {
					final String entityClassName = enversService.getEntitiesConfigurations().get( entityName ).getEntityClassName();
					final Class entityClass = ReflectionTools.loadClass(
							entityClassName,
							enversService.getClassLoaderService()
					);
					final ToOneDelegateSessionImplementor delegate = new ToOneDelegateSessionImplementor(
							versionsReader,
							entityClass,
							entityId,
							revision,
							RevisionType.DEL.equals(
									versionsEntity.get(
											enversService.getAuditEntitiesConfiguration().getRevisionTypePropName()
									)
							),
							enversService
					);
					originalId.put(
							key,
							versionsReader.getSessionImplementor()
									.getFactory()
									.getMetamodel()
									.entityPersister( entityName )
									.createProxy( entityId, delegate )
					);
				}
			}
		}
	}
