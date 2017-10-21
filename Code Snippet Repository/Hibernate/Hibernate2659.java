	private TypeDiscriminatorMetadata buildTypeDiscriminatorMetadata() {
		final String aliasToUse = getTableAlias();
		Queryable queryable = getQueryable();
		if ( queryable == null ) {
			QueryableCollection collection = getQueryableCollection();
			if ( ! collection.getElementType().isEntityType() ) {
				throw new QueryException( "type discrimination cannot be applied to value collection [" + collection.getRole() + "]" );
			}
			queryable = (Queryable) collection.getElementPersister();
		}

		handlePropertyBeingDereferenced( getDataType(), DISCRIMINATOR_PROPERTY_NAME );

		return new TypeDiscriminatorMetadataImpl( queryable.getTypeDiscriminatorMetadata(), aliasToUse );
	}
