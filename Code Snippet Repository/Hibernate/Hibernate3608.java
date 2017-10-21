	@Override
	@SuppressWarnings("")
	protected List getResultList(List results, ResultTransformer resultTransformer) throws QueryException {
		// meant to handle dynamic instantiation queries...(Copy from QueryLoader)
		HolderInstantiator holderInstantiator = HolderInstantiator.getHolderInstantiator(
				null,
				resultTransformer,
				getReturnAliasesForTransformer()
		);
		if ( holderInstantiator.isRequired() ) {
			for ( int i = 0; i < results.size(); i++ ) {
				Object[] row = (Object[]) results.get( i );
				Object result = holderInstantiator.instantiate( row );
				results.set( i, result );
			}

			return resultTransformer.transformList( results );
		}
		else {
			return results;
		}
	}
