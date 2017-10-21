	@Override
	public synchronized Serializable generate(AccessCallback callback) {
		final GenerationState generationState = locateGenerationState( callback.getTenantIdentifier() );

		if ( generationState.hiValue == null ) {
			generationState.value = callback.getNextValue();
			// unfortunately not really safe to normalize this
			// to 1 as an initial value like we do the others
			// because we would not be able to control this if
			// we are using a sequence...
			if ( generationState.value.lt( 1 ) ) {
				log.pooledOptimizerReportedInitialValue( generationState.value );
			}
			// the call to obtain next-value just gave us the initialValue
			if ( ( initialValue == -1
					&& generationState.value.lt( incrementSize ) )
					|| generationState.value.eq( initialValue ) ) {
				generationState.hiValue = callback.getNextValue();
			}
			else {
				generationState.hiValue = generationState.value;
				generationState.value = generationState.hiValue.copy().subtract( incrementSize - 1 );
			}
		}
		else if ( generationState.value.gt( generationState.hiValue ) ) {
			generationState.hiValue = callback.getNextValue();
			generationState.value = generationState.hiValue.copy().subtract( incrementSize - 1 );
		}

		return generationState.value.makeValueThenIncrement();
	}
