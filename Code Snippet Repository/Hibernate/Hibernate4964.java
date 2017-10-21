	private static EntityPersister resolveEntityPersister(
			Object entity,
			AssociationType associationType,
			SharedSessionContractImplementor session,
			SessionFactoryImplementor sessionFactory) {
		assert sessionFactory != null;

		if ( session != null ) {
			return session.getEntityPersister(
					associationType.getAssociatedEntityName( session.getFactory() ),
					entity
			);
		}

		String entityName = null;
		for ( EntityNameResolver entityNameResolver : sessionFactory.getMetamodel().getEntityNameResolvers() ) {
			entityName = entityNameResolver.resolveEntityName( entity );
			if ( entityName != null ) {
				break;
			}
		}
		if ( entityName == null ) {
			// old fall-back
			entityName = entity.getClass().getName();
		}

		return sessionFactory.getMetamodel().entityPersister( entityName );
	}
