	private void handlePersistenceException(PersistenceException e) {
		if ( e instanceof NoResultException ) {
			return;
		}
		if ( e instanceof NonUniqueResultException ) {
			return;
		}
		if ( e instanceof LockTimeoutException ) {
			return;
		}
		if ( e instanceof QueryTimeoutException ) {
			return;
		}

		try {
			sharedSessionContract.markForRollbackOnly();
		}
		catch (Exception ne) {
			//we do not want the subsequent exception to swallow the original one
			log.unableToMarkForRollbackOnPersistenceException( ne );
		}
	}
