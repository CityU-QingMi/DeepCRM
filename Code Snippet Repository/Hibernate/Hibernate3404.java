		public void release() {
			if ( !initialized ) {
				// log
				return;
			}

			injectionTarget.preDestroy( listenerInstance );
			injectionTarget.dispose( listenerInstance );
			creationalContext.release();
		}
