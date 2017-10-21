	@Override
	protected List getResultList(List results, ResultTransformer resultTransformer) throws QueryException {
		if ( holderClass != null ) {
			for ( int i = 0; i < results.size(); i++ ) {
				Object[] row = (Object[]) results.get( i );
				try {
					results.set( i, holderConstructor.newInstance( row ) );
				}
				catch (Exception e) {
					throw new QueryException( "could not instantiate: " + holderClass, e );
				}
			}
		}
		return results;
	}
