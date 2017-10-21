	private void processPropertyReferences() {
		if ( delayedPropertyReferenceHandlers == null ) {
			return;
		}
		log.debug( "Processing association property references" );

		for ( DelayedPropertyReferenceHandler delayedPropertyReferenceHandler : delayedPropertyReferenceHandlers ) {
			delayedPropertyReferenceHandler.process( this );
		}

		delayedPropertyReferenceHandlers.clear();
	}
