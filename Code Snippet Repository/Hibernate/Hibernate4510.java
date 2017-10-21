	@Override
	protected boolean applyNativeQueryLockMode(Object value) {
		if ( LockMode.class.isInstance( value ) ) {
			applyHibernateLockModeHint( (LockMode) value );
		}
		else if ( LockModeType.class.isInstance( value ) ) {
			applyLockModeTypeHint( (LockModeType) value );
		}
		else {
			throw new IllegalArgumentException(
					String.format(
							"Native lock-mode hint [%s] must specify %s or %s.  Encountered type : %s",
							HINT_NATIVE_LOCKMODE,
							LockMode.class.getName(),
							LockModeType.class.getName(),
							value.getClass().getName()
					)
			);
		}

		return true;
	}
