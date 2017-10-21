	public static AttributeSource resolveAttributeSource(SessionFactoryImplementor sessionFactory, ManagedType managedType) {
		if ( EmbeddableTypeImpl.class.isInstance( managedType ) ) {
			return new ComponentAttributeSource( ( (EmbeddableTypeImpl) managedType ).getHibernateType() );
		}
		else if ( IdentifiableType.class.isInstance( managedType ) ) {
			final String entityName = managedType.getJavaType().getName();
			log.debugf( "Attempting to resolve managed type as entity using %s", entityName );
			return new EntityPersisterAttributeSource( sessionFactory.getEntityPersister( entityName ) );
		}
		else {
			throw new IllegalArgumentException(
					String.format( "Unknown ManagedType implementation [%s]", managedType.getClass() )
			);
		}
	}
