	@Override
	public synchronized Serializable generate(AccessCallback callback) {
		final GenerationState generationState = locateGenerationState( callback.getTenantIdentifier() );

		if ( generationState.lastSourceValue == null
				|| ! generationState.value.lt( generationState.upperLimitValue ) ) {
			generationState.lastSourceValue = callback.getNextValue();
			generationState.upperLimitValue = generationState.lastSourceValue.copy().add( incrementSize );
			generationState.value = generationState.lastSourceValue.copy();
			// handle cases where initial-value is less that one (hsqldb for instance).
			while ( generationState.value.lt( 1 ) ) {
				generationState.value.increment();
			}
		}
		return generationState.value.makeValueThenIncrement();
	}
