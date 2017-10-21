	@SuppressWarnings({ "" })
	private String getForUpdateString(String aliases, LockMode lockMode, int timeout) {
		switch ( lockMode ) {
			case UPGRADE:
				return getForUpdateString( aliases );
			case PESSIMISTIC_READ:
				return getReadLockString( aliases, timeout );
			case PESSIMISTIC_WRITE:
				return getWriteLockString( aliases, timeout );
			case UPGRADE_NOWAIT:
			case FORCE:
			case PESSIMISTIC_FORCE_INCREMENT:
				return getForUpdateNowaitString( aliases );
			case UPGRADE_SKIPLOCKED:
				return getForUpdateSkipLockedString( aliases );
			default:
				return "";
		}
	}
