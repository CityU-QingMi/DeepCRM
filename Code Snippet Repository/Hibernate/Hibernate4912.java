	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		CacheableResultTransformer that = ( CacheableResultTransformer ) o;

		return tupleLength == that.tupleLength
				&& tupleSubsetLength == that.tupleSubsetLength
				&& Arrays.equals( includeInTuple, that.includeInTuple )
				&& Arrays.equals( includeInTransformIndex, that.includeInTransformIndex );
	}
