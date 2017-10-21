		@Override
		public void noCascade(
				EventSource session,
				Object parent,
				EntityPersister persister,
				Type propertyType,
				int propertyIndex) {
			if ( propertyType.isEntityType() ) {
				Object child = persister.getPropertyValue( parent, propertyIndex );
				String childEntityName = ((EntityType) propertyType).getAssociatedEntityName( session.getFactory() );

				if ( child != null
						&& !isInManagedState( child, session )
						&& !(child instanceof HibernateProxy) //a proxy cannot be transient and it breaks ForeignKeys.isTransient
						&& ForeignKeys.isTransient( childEntityName, child, null, session ) ) {
					String parentEntiytName = persister.getEntityName();
					String propertyName = persister.getPropertyNames()[propertyIndex];
					throw new TransientPropertyValueException(
							"object references an unsaved transient instance - save the transient instance before flushing",
							childEntityName,
							parentEntiytName,
							propertyName
					);

				}
			}
		}
