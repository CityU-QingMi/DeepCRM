	QueryableCollection getCollectionPersister(String role) throws QueryException {
		try {
			return (QueryableCollection) getFactory().getMetamodel().collectionPersister( role );
		}
		catch (ClassCastException cce) {
			throw new QueryException( "collection role is not queryable: " + role );
		}
		catch (Exception e) {
			throw new QueryException( "collection role not found: " + role );
		}
	}
