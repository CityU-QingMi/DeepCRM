	private CacheableResultTransformer(boolean[] includeInTuple, boolean[] includeInTransform) {
		if ( includeInTuple == null ) {
			throw new IllegalArgumentException( "includeInTuple cannot be null" );
		}
		this.includeInTuple = includeInTuple;
		tupleLength = ArrayHelper.countTrue( includeInTuple );
		tupleSubsetLength = (
				includeInTransform == null ?
						tupleLength :
						ArrayHelper.countTrue( includeInTransform )
		);
		if ( tupleSubsetLength == tupleLength ) {
			includeInTransformIndex = null;
		}
		else {
			includeInTransformIndex = new int[tupleSubsetLength];
			for ( int i = 0, j = 0 ; i < includeInTransform.length ; i++ ) {
				if ( includeInTransform[ i ] ) {
					includeInTransformIndex[ j ] =  i;
					j++;
				}
			}
		}
	}
