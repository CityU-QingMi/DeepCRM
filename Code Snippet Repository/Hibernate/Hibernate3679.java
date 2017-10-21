	@SuppressWarnings("")
	@Override
	protected List getResultList(List results, ResultTransformer resultTransformer) throws QueryException {
		// meant to handle dynamic instantiation queries...
		HolderInstantiator holderInstantiator = buildHolderInstantiator( resultTransformer );
		if ( holderInstantiator.isRequired() ) {
			for ( int i = 0; i < results.size(); i++ ) {
				Object[] row = (Object[]) results.get( i );
				Object result = holderInstantiator.instantiate( row );
				results.set( i, result );
			}

			if ( !hasSelectNew() && resultTransformer != null ) {
				return resultTransformer.transformList( results );
			}
			else {
				return results;
			}
		}
		else {
			return results;
		}
	}
