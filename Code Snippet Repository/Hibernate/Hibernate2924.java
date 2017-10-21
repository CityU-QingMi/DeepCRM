	@Override
	public synchronized Serializable generate(AccessCallback callback) {
		final GenerationState generationState = locateGenerationState( callback.getTenantIdentifier() );

		if ( generationState.lo > generationState.maxLo ) {
			generationState.lastSourceValue = callback.getNextValue();
			generationState.lo = generationState.lastSourceValue.eq( 0 ) ? 1 : 0;
			generationState.hi = generationState.lastSourceValue.copy().multiplyBy( generationState.maxLo + 1 );
		}
		generationState.value = generationState.hi.copy().add( generationState.lo++ );
		return generationState.value.makeValue();
	}
