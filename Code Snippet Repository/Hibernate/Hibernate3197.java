	public static LockModeType convertToLockModeType(LockMode lockMode) {
		if ( lockMode == LockMode.NONE ) {
			return LockModeType.NONE;
		}
		else if ( lockMode == LockMode.OPTIMISTIC || lockMode == LockMode.READ ) {
			return LockModeType.OPTIMISTIC;
		}
		else if ( lockMode == LockMode.OPTIMISTIC_FORCE_INCREMENT || lockMode == LockMode.WRITE ) {
			return LockModeType.OPTIMISTIC_FORCE_INCREMENT;
		}
		else if ( lockMode == LockMode.PESSIMISTIC_READ ) {
			return LockModeType.PESSIMISTIC_READ;
		}
		else if ( lockMode == LockMode.PESSIMISTIC_WRITE
				|| lockMode == LockMode.UPGRADE
				|| lockMode == LockMode.UPGRADE_NOWAIT
				|| lockMode == LockMode.UPGRADE_SKIPLOCKED) {
			return LockModeType.PESSIMISTIC_WRITE;
		}
		else if ( lockMode == LockMode.PESSIMISTIC_FORCE_INCREMENT
				|| lockMode == LockMode.FORCE ) {
			return LockModeType.PESSIMISTIC_FORCE_INCREMENT;
		}
		throw new AssertionFailure( "unhandled lock mode " + lockMode );
	}
