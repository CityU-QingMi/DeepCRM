	protected QueryableCollection getQueryableCollection(
			String entityName,
			String propertyName,
			SessionFactoryImplementor factory) throws HibernateException {
		final PropertyMapping ownerMapping = (PropertyMapping) factory.getEntityPersister( entityName );
		final Type type = ownerMapping.toType( propertyName );
		if ( !type.isCollectionType() ) {
			throw new MappingException(
					"Property path [" + entityName + "." + propertyName + "] does not reference a collection"
			);
		}

		final String role = ( (CollectionType) type ).getRole();
		try {
			return (QueryableCollection) factory.getCollectionPersister( role );
		}
		catch ( ClassCastException cce ) {
			throw new QueryException( "collection role is not queryable: " + role );
		}
		catch ( Exception e ) {
			throw new QueryException( "collection role not found: " + role );
		}
	}
