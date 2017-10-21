	@Override
	public String getAssociatedEntityName(SessionFactoryImplementor factory)
			throws MappingException {
		try {
			
			QueryableCollection collectionPersister = (QueryableCollection) factory
					.getCollectionPersister( role );
			
			if ( !collectionPersister.getElementType().isEntityType() ) {
				throw new MappingException( 
						"collection was not an association: " + 
						collectionPersister.getRole() 
				);
			}
			
			return collectionPersister.getElementPersister().getEntityName();
			
		}
		catch (ClassCastException cce) {
			throw new MappingException( "collection role is not queryable " + role );
		}
	}
