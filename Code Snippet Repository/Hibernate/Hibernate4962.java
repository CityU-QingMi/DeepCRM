		@Override
		public void setIdentifier(Object entity, Serializable id, EntityMode entityMode, SharedSessionContractImplementor session) {
			final Object[] extractedValues = mappedIdentifierType.getPropertyValues( id, entityMode );
			final Object[] injectionValues = new Object[extractedValues.length];
			final PersistenceContext persistenceContext = session.getPersistenceContext();
			for ( int i = 0; i < virtualIdComponent.getSubtypes().length; i++ ) {
				final Type virtualPropertyType = virtualIdComponent.getSubtypes()[i];
				final Type idClassPropertyType = mappedIdentifierType.getSubtypes()[i];
				if ( virtualPropertyType.isEntityType() && !idClassPropertyType.isEntityType() ) {
					if ( session == null ) {
						throw new AssertionError(
								"Deprecated version of getIdentifier (no session) was used but session was required"
						);
					}
					final String associatedEntityName = ( (EntityType) virtualPropertyType ).getAssociatedEntityName();
					final EntityKey entityKey = session.generateEntityKey(
							(Serializable) extractedValues[i],
							sessionFactory.getMetamodel().entityPersister( associatedEntityName )
					);
					// it is conceivable there is a proxy, so check that first
					Object association = persistenceContext.getProxy( entityKey );
					if ( association == null ) {
						// otherwise look for an initialized version
						association = persistenceContext.getEntity( entityKey );
					}
					injectionValues[i] = association;
				}
				else {
					injectionValues[i] = extractedValues[i];
				}
			}
			virtualIdComponent.setPropertyValues( entity, injectionValues, entityMode );
		}
