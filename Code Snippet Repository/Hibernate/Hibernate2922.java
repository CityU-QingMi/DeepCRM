	@Override
	public synchronized Serializable generate(AccessCallback callback) {
		final GenerationState generationState = locateGenerationState( callback.getTenantIdentifier() );

		if ( generationState.lastSourceValue == null ) {
			// first call, so initialize ourselves.  we need to read the database
			// value and set up the 'bucket' boundaries
			generationState.lastSourceValue = callback.getNextValue();
			while ( generationState.lastSourceValue.lt( 1 ) ) {
				generationState.lastSourceValue = callback.getNextValue();
			}
			// upperLimit defines the upper end of the bucket values
			generationState.upperLimit = generationState.lastSourceValue.copy().multiplyBy( incrementSize ).increment();
			// initialize value to the low end of the bucket
			generationState.value = generationState.upperLimit.copy().subtract( incrementSize );
		}
		else if ( ! generationState.upperLimit.gt( generationState.value ) ) {
			generationState.lastSourceValue = callback.getNextValue();
			generationState.upperLimit = generationState.lastSourceValue.copy().multiplyBy( incrementSize ).increment();
			generationState.value = generationState.upperLimit.copy().subtract( incrementSize );
		}
		return generationState.value.makeValueThenIncrement();
	}
