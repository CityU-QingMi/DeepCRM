	private static CacheableResultTransformer create(
			TupleSubsetResultTransformer transformer,
			String[] aliases,
			boolean[] includeInTuple) {
		if ( transformer == null ) {
			throw new IllegalArgumentException( "transformer cannot be null" );
		}
		int tupleLength = ArrayHelper.countTrue( includeInTuple );
		if ( aliases != null && aliases.length != tupleLength ) {
			throw new IllegalArgumentException(
					"if aliases is not null, then the length of aliases[] must equal the number of true elements in includeInTuple; " +
							"aliases.length=" + aliases.length + "tupleLength=" + tupleLength
			);
		}
		return new CacheableResultTransformer(
				includeInTuple,
				transformer.includeInTransform( aliases, tupleLength )
		);
	}
