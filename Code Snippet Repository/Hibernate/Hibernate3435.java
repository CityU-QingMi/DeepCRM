	public static LockMode interpretLockMode(Object value) {
		if ( value == null ) {
			return LockMode.NONE;
		}
		if ( LockMode.class.isInstance( value ) ) {
			return (LockMode) value;
		}
		else if ( LockModeType.class.isInstance( value ) ) {
			return getLockMode( (LockModeType) value );
		}
		else if ( String.class.isInstance( value ) ) {
			// first try LockMode name
			LockMode lockMode = LockMode.valueOf( (String) value );
			if ( lockMode == null ) {
				try {
					lockMode = getLockMode( LockModeType.valueOf( (String) value ) );
				}
				catch ( Exception ignore ) {
				}
			}
			if ( lockMode != null ) {
				return lockMode;
			}
		}

		throw new IllegalArgumentException( "Unknown lock mode source : " + value );
	}
