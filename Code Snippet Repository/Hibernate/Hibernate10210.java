	private boolean isNonIdentifierWhereConditionsRequired(String entityName, String propertyName, SessionImplementor session) {
		final Type propertyType = session.getSessionFactory().getMetamodel().entityPersister( entityName ).getPropertyType( propertyName );
		if ( propertyType.isCollectionType() ) {
			final CollectionType collectionType = (CollectionType) propertyType;
			final Type collectionElementType = collectionType.getElementType( session.getSessionFactory() );
			if ( collectionElementType instanceof ComponentType ) {
				// required for Embeddables
				return true;
			}
			else if ( collectionElementType instanceof MaterializedClobType || collectionElementType instanceof MaterializedNClobType ) {
				// for Map<> using @Lob annotations
				return collectionType instanceof MapType;
			}
		}
		return false;
	}
