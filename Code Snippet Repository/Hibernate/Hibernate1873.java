	protected String determineSql(int timeout) {
		if ( timeout == LockOptions.WAIT_FOREVER) {
			return waitForeverSql;
		}
		else if ( timeout == LockOptions.NO_WAIT) {
			return getNoWaitSql();
		}
		else if ( timeout == LockOptions.SKIP_LOCKED) {
			return getSkipLockedSql();
		}
		else {
			return generateLockString( timeout );
		}
	}
