		@Override
		public Object getIdentifier(Object entity, EntityMode entityMode, SharedSessionContractImplementor session) {
			final Object id = mappedIdentifierType.instantiate( entityMode );
			final Object[] propertyValues = virtualIdComponent.getPropertyValues( entity, entityMode );
			final Type[] subTypes = virtualIdComponent.getSubtypes();
			final Type[] copierSubTypes = mappedIdentifierType.getSubtypes();
			final int length = subTypes.length;
			for ( int i = 0; i < length; i++ ) {
				if ( propertyValues[i] == null ) {
					throw new HibernateException( "No part of a composite identifier may be null" );
				}
				//JPA 2 @MapsId + @IdClass points to the pk of the entity
				if ( subTypes[i].isAssociationType() && !copierSubTypes[i].isAssociationType()  ) {
					propertyValues[i] = determineEntityIdPersistIfNecessary(
							propertyValues[i],
							(AssociationType) subTypes[i],
							session,
							sessionFactory
					);
				}
			}
			mappedIdentifierType.setPropertyValues( id, propertyValues, entityMode );
			return id;
		}
