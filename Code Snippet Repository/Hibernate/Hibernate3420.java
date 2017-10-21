	@Override
	public void doAction(boolean successful, SessionImplementor session) {
		if ( session.isClosed() ) {
			log.trace( "Session was closed; nothing to do" );
			return;
		}

		if ( !successful && session.getTransactionCoordinator().getTransactionCoordinatorBuilder().isJta() ) {
			session.clear();
		}
	}
