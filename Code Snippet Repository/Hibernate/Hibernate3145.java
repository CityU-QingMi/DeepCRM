	@Override
	@SuppressWarnings("")
	public <T> QueryImplementor<T> createQuery(
			String jpaqlString,
			Class<T> resultClass,
			Selection selection,
			QueryOptions queryOptions) {
		try {
			final QueryImplementor query = createQuery( jpaqlString );

			if ( queryOptions.getValueHandlers() == null ) {
				if ( queryOptions.getResultMetadataValidator() != null ) {
					queryOptions.getResultMetadataValidator().validate( query.getReturnTypes() );
				}
			}

			// determine if we need a result transformer
			List tupleElements = Tuple.class.equals( resultClass )
					? ( (CompoundSelectionImpl<Tuple>) selection ).getCompoundSelectionItems()
					: null;
			if ( queryOptions.getValueHandlers() != null || tupleElements != null ) {
				query.setResultTransformer(
						new CriteriaQueryTupleTransformer( queryOptions.getValueHandlers(), tupleElements )
				);
			}

			return query;
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}
