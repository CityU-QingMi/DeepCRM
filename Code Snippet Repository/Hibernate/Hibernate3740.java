	@Override
	public void addJoin(Join join) {
		if ( JoinDefinedByMetadata.class.isInstance( join ) ) {
			final JoinDefinedByMetadata joinDefinedByMetadata = (JoinDefinedByMetadata) join;
			if ( joinDefinedByMetadata.getJoinedPropertyName().equals( CollectionPropertyNames.COLLECTION_ELEMENTS ) ) {
				if ( elementJoin == null ) {
					elementJoin = joinDefinedByMetadata;
				}
				else {
					throw new IllegalStateException( "Attempt to add an element join, but an element join already exists." );
				}
			}
			else if ( joinDefinedByMetadata.getJoinedPropertyName().equals( CollectionPropertyNames.COLLECTION_INDICES ) ) {
				if ( indexJoin == null ) {
					indexJoin = joinDefinedByMetadata;
				}
				else {
					throw new IllegalStateException( "Attempt to add an index join, but an index join already exists." );
				}
			}
			else {
				throw new IllegalArgumentException(
						String.format(
								"Collection propertyName must be either %s or %s; instead the joined property name was %s.",
								CollectionPropertyNames.COLLECTION_ELEMENTS,
								CollectionPropertyNames.COLLECTION_INDICES,
								joinDefinedByMetadata.getJoinedPropertyName()
						)
				);
			}
		}
		internalGetJoins().add( join );
	}
