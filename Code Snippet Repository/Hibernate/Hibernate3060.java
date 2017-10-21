		@Override
		public Session openSession() {
			log.tracef( "Opening Hibernate Session.  tenant=%s, owner=%s", tenantIdentifier, sessionOwner );
			final SessionImpl session = new SessionImpl( sessionFactory, this );

			for ( SessionEventListener listener : listeners ) {
				session.getEventListenerManager().addListener( listener );
			}

			return session;
		}
