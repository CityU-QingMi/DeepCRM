	public QueryableCollection getCollectionPersister(String role) {
		try {
			return (QueryableCollection) sfi.getMetamodel().collectionPersister( role );
		}
		catch ( ClassCastException cce ) {
			throw new QueryException( "collection is not queryable: " + role );
		}
		catch ( Exception e ) {
			throw new QueryException( "collection not found: " + role );
		}
	}
