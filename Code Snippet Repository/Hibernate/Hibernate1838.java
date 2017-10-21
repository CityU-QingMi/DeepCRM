	@Override
	public String appendLockHint(LockOptions lockOptions, String tableName) {
		final LockMode mode = lockOptions.getLockMode();
		switch ( mode ) {
			case UPGRADE:
			case UPGRADE_NOWAIT:
			case PESSIMISTIC_WRITE:
			case WRITE:
				return tableName + " with (updlock, rowlock)";
			case PESSIMISTIC_READ:
				return tableName + " with (holdlock, rowlock)";
			case UPGRADE_SKIPLOCKED:
				return tableName + " with (updlock, rowlock, readpast)";
			default:
				return tableName;
		}
	}
