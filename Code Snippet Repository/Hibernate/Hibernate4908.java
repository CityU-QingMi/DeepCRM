	@SuppressWarnings( {""})
	public List retransformResults(
			List transformedResults,
			String[] aliases,
			ResultTransformer transformer,
			boolean[] includeInTuple) {
		if ( transformer == null ) {
			throw new IllegalArgumentException( "transformer cannot be null" );
		}
		if ( ! this.equals( create( transformer, aliases, includeInTuple ) ) ) {
			throw new IllegalStateException(
					"this CacheableResultTransformer is inconsistent with specified arguments; cannot re-transform"
			);
		}
		boolean requiresRetransform = true;
		String[] aliasesToUse = aliases == null ? null : index( ( aliases.getClass() ), aliases );
		if ( transformer == ACTUAL_TRANSFORMER ) {
			requiresRetransform = false;
		}
		else if ( transformer instanceof TupleSubsetResultTransformer ) {
			requiresRetransform =  ! ( ( TupleSubsetResultTransformer ) transformer ).isTransformedValueATupleElement(
					aliasesToUse,
					tupleLength
			);
		}
		if ( requiresRetransform ) {
			for ( int i = 0 ; i < transformedResults.size() ; i++ ) {
				Object[] tuple = ACTUAL_TRANSFORMER.untransformToTuple(
									transformedResults.get( i ),
									tupleSubsetLength == 1
				);
				transformedResults.set( i, transformer.transformTuple( tuple, aliasesToUse ) );
			}
		}
		return transformedResults;
	}
