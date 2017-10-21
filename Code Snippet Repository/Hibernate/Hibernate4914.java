	List untransformToTuples(List results, boolean isSingleResult) {
		// untransform only if necessary; if transformed, do it in place;
		if ( isSingleResult ) {
			for ( int i = 0 ; i < results.size() ; i++ ) {
				Object[] tuple = untransformToTuple( results.get( i ), isSingleResult);
				results.set( i, tuple );
			}
		}
		return results;
	}
