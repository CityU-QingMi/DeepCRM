	@SuppressWarnings( {""})
	public List untransformToTuples(List results) {
		if ( includeInTransformIndex == null ) {
			results = ACTUAL_TRANSFORMER.untransformToTuples(
					results,
					tupleSubsetLength == 1
			);
		}
		else {
			for ( int i = 0 ; i < results.size() ; i++ ) {
				Object[] tuple = ACTUAL_TRANSFORMER.untransformToTuple(
									results.get( i ),
									tupleSubsetLength == 1
				);
				results.set( i, unindex( tuple.getClass(), tuple ) );
			}

		}
		return results;
	}
