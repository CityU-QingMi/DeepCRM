	private EntityMetamodel getDeclarerEntityMetamodel(AbstractIdentifiableType<?> ownerType) {
		final Type.PersistenceType persistenceType = ownerType.getPersistenceType();
		if ( persistenceType == Type.PersistenceType.ENTITY ) {
			return context.getSessionFactory()
					.getMetamodel()
					.entityPersister( ownerType.getTypeName() )
					.getEntityMetamodel();
		}
		else if ( persistenceType == Type.PersistenceType.MAPPED_SUPERCLASS ) {
			PersistentClass persistentClass =
					context.getPersistentClassHostingProperties( (MappedSuperclassTypeImpl<?>) ownerType );
			return context.getSessionFactory()
					.getMetamodel()
					.entityPersister( persistentClass.getClassName() )
					.getEntityMetamodel();
		}
		else {
			throw new AssertionFailure( "Cannot get the metamodel for PersistenceType: " + persistenceType );
		}
	}
