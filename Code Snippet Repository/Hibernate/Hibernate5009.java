	private EntityPersister guessEntityPersister(Object object) {
		if ( scope == null ) {
			return null;
		}

		String entityName = null;

		// this code is largely copied from Session's bestGuessEntityName
		Object entity = object;
		if ( entity instanceof HibernateProxy ) {
			final LazyInitializer initializer = ( (HibernateProxy) entity ).getHibernateLazyInitializer();
			if ( initializer.isUninitialized() ) {
				entityName = initializer.getEntityName();
			}
			entity = initializer.getImplementation();
		}

		if ( entityName == null ) {
			for ( EntityNameResolver resolver : scope.resolveFactory().getMetamodel().getEntityNameResolvers() ) {
				entityName = resolver.resolveEntityName( entity );
				if ( entityName != null ) {
					break;
				}
			}
		}

		if ( entityName == null ) {
			// the old-time stand-by...
			entityName = object.getClass().getName();
		}

		return scope.resolveFactory().getMetamodel().entityPersister( entityName );
	}
