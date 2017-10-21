		private void addParentChildEntityNames(AbstractEntityInsertAction action, BatchIdentifier batchIdentifier) {
			Object[] propertyValues = action.getState();
			ClassMetadata classMetadata = action.getPersister().getClassMetadata();
			if ( classMetadata != null ) {
				Type[] propertyTypes = classMetadata.getPropertyTypes();

				for ( int i = 0; i < propertyValues.length; i++ ) {
					Object value = propertyValues[i];
					Type type = propertyTypes[i];
					if ( type.isEntityType() && value != null ) {
						EntityType entityType = (EntityType) type;
						String entityName = entityType.getName();
						String rootEntityName = action.getSession().getFactory().getMetamodel().entityPersister( entityName ).getRootEntityName();

						if ( entityType.isOneToOne() &&
								OneToOneType.class.cast( entityType ).getForeignKeyDirection() == ForeignKeyDirection.TO_PARENT ) {
							batchIdentifier.getChildEntityNames().add( entityName );
							if ( !rootEntityName.equals( entityName ) ) {
								batchIdentifier.getChildEntityNames().add( rootEntityName );
							}
						}
						else {
							batchIdentifier.getParentEntityNames().add( entityName );
							if ( !rootEntityName.equals( entityName ) ) {
								batchIdentifier.getParentEntityNames().add( rootEntityName );
							}
						}
					}
					else if ( type.isCollectionType() && value != null ) {
						CollectionType collectionType = (CollectionType) type;
						final SessionFactoryImplementor sessionFactory = ( (SessionImplementor) action.getSession() )
								.getSessionFactory();
						if ( collectionType.getElementType( sessionFactory ).isEntityType() ) {
							String entityName = collectionType.getAssociatedEntityName( sessionFactory );
							String rootEntityName = action.getSession().getFactory().getMetamodel().entityPersister( entityName ).getRootEntityName();
							batchIdentifier.getChildEntityNames().add( entityName );
							if ( !rootEntityName.equals( entityName ) ) {
								batchIdentifier.getChildEntityNames().add( rootEntityName );
							}
						}
					}
				}
			}
		}
