	public CollectionPropertyReference getCollectionPropertyReference(final String propertyName) {
		if ( queryableCollection == null ) {
			throw new QueryException( "Not a collection reference" );
		}

		final PropertyMapping collectionPropertyMapping;

		if ( queryableCollection.isManyToMany()
				&& queryableCollection.hasIndex()
				&& SPECIAL_MANY2MANY_TREATMENT_FUNCTION_NAMES.contains( propertyName ) ) {
			collectionPropertyMapping = new SpecialManyToManyCollectionPropertyMapping();
		}
		else if ( CollectionProperties.isCollectionProperty( propertyName ) ) {
			if ( this.collectionPropertyMapping == null ) {
				this.collectionPropertyMapping = new CollectionPropertyMapping( queryableCollection );
			}
			collectionPropertyMapping = this.collectionPropertyMapping;
		}
		else {
			collectionPropertyMapping = queryableCollection;
		}

		return new CollectionPropertyReference() {
			@Override
			public Type getType() {
				return collectionPropertyMapping.toType( propertyName );
			}

			@Override
			public String[] toColumns(final String tableAlias) {
				if ( propertyName.equalsIgnoreCase( "index" ) ) {
					return collectionPropertyMapping.toColumns( tableAlias, propertyName );
				}

				Map enabledFilters = fromElement.getWalker().getEnabledFilters();
				String subquery = CollectionSubqueryFactory.createCollectionSubquery(
						joinSequence.copy().setUseThetaStyle( true ),
						enabledFilters,
						collectionPropertyMapping.toColumns( tableAlias, propertyName )
				);
				LOG.debugf( "toColumns(%s,%s) : subquery = %s", tableAlias, propertyName, subquery );
				return new String[] {"(" + subquery + ")"};
			}
		};
	}
