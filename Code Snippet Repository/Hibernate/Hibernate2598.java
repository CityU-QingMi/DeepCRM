	@Override
	public void resolve(
			boolean generateJoin,
			boolean implicitJoin,
			String classAlias,
			AST parent,
			AST parentPredicate) throws SemanticException {
		if ( mapFromElement == null ) {
			final FromReferenceNode mapReference = getMapReference();
			mapReference.resolve( true, true );

			FromElement sourceFromElement = null;
			if ( isAliasRef( mapReference ) ) {
				final QueryableCollection collectionPersister = mapReference.getFromElement().getQueryableCollection();
				if ( Map.class.isAssignableFrom( collectionPersister.getCollectionType().getReturnedClass() ) ) {
					sourceFromElement = mapReference.getFromElement();
				}
			}
			else {
				if ( mapReference.getDataType().isCollectionType() ) {
					final CollectionType collectionType = (CollectionType) mapReference.getDataType();
					if ( Map.class.isAssignableFrom( collectionType.getReturnedClass() ) ) {
						sourceFromElement = mapReference.getFromElement();
					}
				}
			}

			if ( sourceFromElement == null ) {
				throw nonMap();
			}

			mapFromElement = sourceFromElement;
		}

		setFromElement( mapFromElement );
		setDataType( resolveType( mapFromElement.getQueryableCollection() ) );
		this.columns = resolveColumns( mapFromElement.getQueryableCollection() );
		initText( this.columns );
		setFirstChild( null );
	}
