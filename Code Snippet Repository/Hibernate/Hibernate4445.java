		public MapKeyAttribute(CriteriaBuilderImpl criteriaBuilder, MapAttribute<?, K, ?> attribute) {
			this.attribute = attribute;
			this.jpaType = attribute.getKeyType();
			this.jpaBinableJavaType = attribute.getKeyJavaType();
			this.jpaBindableType = Type.PersistenceType
					.ENTITY.equals( jpaType.getPersistenceType() )
					? BindableType.ENTITY_TYPE
					: BindableType.SINGULAR_ATTRIBUTE;

			String guessedRoleName = determineRole( attribute );
			SessionFactoryImplementor sfi = criteriaBuilder.getEntityManagerFactory().getSessionFactory();
			mapPersister = sfi.getCollectionPersister( guessedRoleName );
			if ( mapPersister == null ) {
				throw new IllegalStateException( "Could not locate collection persister [" + guessedRoleName + "]" );
			}
			mapKeyType = mapPersister.getIndexType();
			if ( mapKeyType == null ) {
				throw new IllegalStateException( "Could not determine map-key type [" + guessedRoleName + "]" );
			}

			this.persistentAttributeType = mapKeyType.isEntityType()
					? PersistentAttributeType.MANY_TO_ONE
					: mapKeyType.isComponentType()
							? PersistentAttributeType.EMBEDDED
							: PersistentAttributeType.BASIC;
		}
