	@Override
	public void setLockMode(LockMode lockMode) {
		switch ( lockMode ) {
			case NONE:
			case READ: {
				setCompressedValue( EnumState.LOCK_MODE, lockMode );
				break;
			}
			default: {
				throw new UnsupportedLockAttemptException( "Lock mode not supported" );
			}
		}
	}
