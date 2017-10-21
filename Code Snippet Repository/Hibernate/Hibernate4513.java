	@Override
	public QueryParameters getQueryParameters() {
		final QueryParameters queryParameters = super.getQueryParameters();
		queryParameters.setCallable( callable );
		queryParameters.setAutoDiscoverScalarTypes( autoDiscoverTypes );
		if ( collectionKey != null ) {
			queryParameters.setCollectionKeys( new Serializable[] {collectionKey} );
		}
		return queryParameters;
	}
