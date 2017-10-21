	@Override
	public void doBeforeTransactionCompletion(SessionImplementor session) {
		if ( workUnits.size() == 0 && undoQueue.size() == 0 ) {
			return;
		}

		if ( !session.getTransactionCoordinator().isActive() ) {
			log.debug( "Skipping envers transaction hook due to non-active (most likely marked-rollback-only) transaction" );
			return;
		}

		// see: http://www.jboss.com/index.html?module=bb&op=viewtopic&p=4178431
		if ( FlushMode.MANUAL.equals( session.getHibernateFlushMode() ) || session.isClosed() ) {
			Session temporarySession = null;
			try {
				temporarySession = session.sessionWithOptions()
						.connection()
						.autoClose( false )
						.connectionHandlingMode( PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION )
						.openSession();
				executeInSession( temporarySession );
				temporarySession.flush();
			}
			finally {
				if ( temporarySession != null ) {
					temporarySession.close();
				}
			}
		}
		else {
			executeInSession( session );

			// Explicitly flushing the session, as the auto-flush may have already happened.
			session.flush();
		}
	}
