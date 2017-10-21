	@Override
	public String resolveEntityName(Object entity) {
		String entityName = interceptor.getEntityName( entity );
		if ( entityName != null ) {
			return entityName;
		}

		for ( EntityNameResolver resolver : sessionFactory.getMetamodel().getEntityNameResolvers() ) {
			entityName = resolver.resolveEntityName( entity );
			if ( entityName != null ) {
				break;
			}
		}

		if ( entityName != null ) {
			return entityName;
		}

		// the old-time stand-by...
		return entity.getClass().getName();
	}
