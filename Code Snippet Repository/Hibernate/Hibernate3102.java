	protected void applyQuerySettingsAndHints(Query query) {
		if ( lockOptions.getLockMode() != LockMode.NONE ) {
			query.setLockMode( getLockMode( lockOptions.getLockMode() ) );
		}
		Object queryTimeout;
		if ( (queryTimeout = getProperties().get( QueryHints.SPEC_HINT_TIMEOUT ) ) != null ) {
			query.setHint( QueryHints.SPEC_HINT_TIMEOUT, queryTimeout );
		}
		Object lockTimeout;
		if( (lockTimeout = getProperties().get( JPA_LOCK_TIMEOUT ))!=null){
			query.setHint( JPA_LOCK_TIMEOUT, lockTimeout );
		}
	}
