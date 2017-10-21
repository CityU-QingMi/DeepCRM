		public void release() {
			if ( !initialized ) {
				log.debug( "Skipping release for CDI listener [" + listenerClass + "] as it was not initialized" );
				return;
			}

			log.debug( "Releasing CDI listener : " + listenerClass );

			injectionTarget.preDestroy( listenerInstance );
			injectionTarget.dispose( listenerInstance );
			creationalContext.release();
		}
