	private Queryable determineAppropriateOwnerPersister(NonScalarReturn ownerDescriptor) {
		String entityName = null;
		if ( ownerDescriptor instanceof RootReturn ) {
			entityName = ( (RootReturn) ownerDescriptor ).getEntityName();
		}
		else if ( ownerDescriptor instanceof CollectionReturn ) {
			CollectionReturn collRtn = (CollectionReturn) ownerDescriptor;
			String role = collRtn.getOwnerEntityName() + "." + collRtn.getOwnerProperty();
			CollectionPersister persister = getFactory().getMetamodel().collectionPersister( role );
			EntityType ownerType = (EntityType) persister.getElementType();
			entityName = ownerType.getAssociatedEntityName( getFactory() );
		}
		else if ( ownerDescriptor instanceof FetchReturn ) {
			FetchReturn fetchRtn = (FetchReturn) ownerDescriptor;
			Queryable persister = determineAppropriateOwnerPersister( fetchRtn.getOwner() );
			Type ownerType = persister.getPropertyType( fetchRtn.getOwnerProperty() );
			if ( ownerType.isEntityType() ) {
				entityName = ( (EntityType) ownerType ).getAssociatedEntityName( getFactory() );
			}
			else if ( ownerType.isCollectionType() ) {
				Type ownerCollectionElementType = ( (CollectionType) ownerType ).getElementType( getFactory() );
				if ( ownerCollectionElementType.isEntityType() ) {
					entityName = ( (EntityType) ownerCollectionElementType ).getAssociatedEntityName( getFactory() );
				}
			}
		}

		if ( entityName == null ) {
			throw new HibernateException( "Could not determine fetch owner : " + ownerDescriptor );
		}

		return (Queryable) getFactory().getMetamodel().entityPersister( entityName );
	}
