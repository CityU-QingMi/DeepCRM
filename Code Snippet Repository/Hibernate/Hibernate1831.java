	@Override
	public String appendLockHint(LockOptions lockOptions, String tableName) {

		LockMode lockMode = lockOptions.getAliasSpecificLockMode( tableName );
		if(lockMode == null) {
			lockMode = lockOptions.getLockMode();
		}

		final String writeLockStr = lockOptions.getTimeOut() == LockOptions.SKIP_LOCKED ? "updlock" : "updlock, holdlock";
		final String readLockStr = lockOptions.getTimeOut() == LockOptions.SKIP_LOCKED ? "updlock" : "holdlock";

		final String noWaitStr = lockOptions.getTimeOut() == LockOptions.NO_WAIT ? ", nowait" : "";
		final String skipLockStr = lockOptions.getTimeOut() == LockOptions.SKIP_LOCKED ? ", readpast" : "";

		switch ( lockMode ) {
			case UPGRADE:
			case PESSIMISTIC_WRITE:
			case WRITE: {
				return tableName + " with (" + writeLockStr + ", rowlock" + noWaitStr + skipLockStr + ")";
			}
			case PESSIMISTIC_READ: {
				return tableName + " with (" + readLockStr + ", rowlock" + noWaitStr + skipLockStr + ")";
			}
			case UPGRADE_SKIPLOCKED:
				return tableName + " with (updlock, rowlock, readpast" + noWaitStr + ")";
			case UPGRADE_NOWAIT:
				return tableName + " with (updlock, holdlock, rowlock, nowait)";
			default: {
				return tableName;
			}
		}
	}
