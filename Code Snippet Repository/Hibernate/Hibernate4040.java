	private static void registerEntityNameResolvers(EntityTuplizer tuplizer, Map<EntityNameResolver,Object> entityNameResolvers) {
		EntityNameResolver[] resolvers = tuplizer.getEntityNameResolvers();
		if ( resolvers == null ) {
			return;
		}

		for ( EntityNameResolver resolver : resolvers ) {
			entityNameResolvers.put( resolver, ENTITY_NAME_RESOLVER_MAP_VALUE );
		}
	}
