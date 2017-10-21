	private CriteriaInfoProvider getPathInfo(String path) {
		StringTokenizer tokens = new StringTokenizer( path, "." );
		String componentPath = "";

		// start with the 'rootProvider'
		CriteriaInfoProvider provider = nameCriteriaInfoMap.get( rootEntityName );

		while ( tokens.hasMoreTokens() ) {
			componentPath += tokens.nextToken();
			final Type type = provider.getType( componentPath );
			if ( type.isAssociationType() ) {
				// CollectionTypes are always also AssociationTypes - but there's not always an associated entity...
				final AssociationType atype = (AssociationType) type;
				final CollectionType ctype = type.isCollectionType() ? (CollectionType) type : null;
				final Type elementType = ( ctype != null ) ? ctype.getElementType( sessionFactory ) : null;
				// is the association a collection of components or value-types? (i.e a colloction of valued types?)
				if ( ctype != null && elementType.isComponentType() ) {
					provider = new ComponentCollectionCriteriaInfoProvider( helper.getCollectionPersister( ctype.getRole() ) );
				}
				else if ( ctype != null && !elementType.isEntityType() ) {
					provider = new ScalarCollectionCriteriaInfoProvider( helper, ctype.getRole() );
				}
				else {
					provider = new EntityCriteriaInfoProvider(
							(Queryable) sessionFactory.getEntityPersister( atype.getAssociatedEntityName( sessionFactory ) )
					);
				}

				componentPath = "";
			}
			else if ( type.isComponentType() ) {
				if ( !tokens.hasMoreTokens() ) {
					throw new QueryException(
							"Criteria objects cannot be created directly on components.  Create a criteria on " +
									"owning entity and use a dotted property to access component property: " + path
					);
				}
				else {
					componentPath += '.';
				}
			}
			else {
				throw new QueryException( "not an association: " + componentPath );
			}
		}

		return provider;
	}
