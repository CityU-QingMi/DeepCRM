		@Override
		public void objectRemoved(NamingEvent evt) {
			final String jndiName = evt.getOldBinding().getName();
			LOG.factoryUnboundFromName( jndiName );

			final String uuid = nameUuidXref.remove( jndiName );
			if ( uuid == null ) {
				// serious problem... but not sure what to do yet
			}
			sessionFactoryMap.remove( uuid );
		}
