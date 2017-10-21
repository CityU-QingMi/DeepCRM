	@Override
	public String getWriteLockString(int timeout) {
		if ( timeout == LockOptions.NO_WAIT ) {
			return " for update nowait";
		}
		else if ( timeout > 0 ) {
			// convert from milliseconds to seconds
			final float seconds = timeout / 1000.0f;
			timeout = Math.round( seconds );
			return " for update wait " + timeout;
		}
		else {
			return " for update";
		}
	}
