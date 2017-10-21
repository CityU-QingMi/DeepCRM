	public EntityPersister requireClassPersister(String name) throws SemanticException {
		EntityPersister cp;
		try {
			cp = findEntityPersisterByName( name );
			if ( cp == null ) {
				throw new QuerySyntaxException( name + " is not mapped" );
			}
		}
		catch ( MappingException e ) {
			throw new DetailedSemanticException( e.getMessage(), e );
		}
		return cp;
	}
