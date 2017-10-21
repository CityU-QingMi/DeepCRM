	public QueryableCollection requireQueryableCollection(String role) throws QueryException {
		try {
			QueryableCollection queryableCollection = (QueryableCollection) sfi.getMetamodel().collectionPersister( role );
			if ( queryableCollection != null ) {
				collectionPropertyMappingByRole.put( role, new CollectionPropertyMapping( queryableCollection ) );
			}
			return queryableCollection;
		}
		catch ( ClassCastException cce ) {
			throw new QueryException( "collection role is not queryable: " + role );
		}
		catch ( Exception e ) {
			throw new QueryException( "collection role not found: " + role );
		}
	}
