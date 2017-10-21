	public static LockMode convertToLockMode(LockModeType lockMode) {
		switch ( lockMode ) {
			case READ:
			case OPTIMISTIC: {
				return LockMode.OPTIMISTIC;
			}
			case OPTIMISTIC_FORCE_INCREMENT:
			case WRITE: {
				return LockMode.OPTIMISTIC_FORCE_INCREMENT;
			}
			case PESSIMISTIC_READ: {
				return LockMode.PESSIMISTIC_READ;
			}
			case PESSIMISTIC_WRITE: {
				return LockMode.PESSIMISTIC_WRITE;
			}
			case PESSIMISTIC_FORCE_INCREMENT: {
				return LockMode.PESSIMISTIC_FORCE_INCREMENT;
			}
			case NONE: {
				return LockMode.NONE;
			}
			default: {
				throw new AssertionFailure( "Unknown LockModeType: " + lockMode );
			}
		}
	}
