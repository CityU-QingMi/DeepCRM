	@SuppressWarnings( {""})
	private String getForUpdateString(LockMode lockMode, int timeout){
		switch ( lockMode ) {
			case UPGRADE:
				return getForUpdateString();
			case PESSIMISTIC_READ:
				return getReadLockString( timeout );
			case PESSIMISTIC_WRITE:
				return getWriteLockString( timeout );
			case UPGRADE_NOWAIT:
			case FORCE:
			case PESSIMISTIC_FORCE_INCREMENT:
				return getForUpdateNowaitString();
			case UPGRADE_SKIPLOCKED:
				return getForUpdateSkipLockedString();
			default:
				return "";
		}
	}
