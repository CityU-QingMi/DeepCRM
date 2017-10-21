	@Override
	public String getWriteLockString(int timeout) {
		if ( timeout > 0 ) {
			return getForUpdateString() + " wait " + timeout;
		}
		else if ( timeout == 0 ) {
			return getForUpdateNowaitString();
		}
		else {
			return getForUpdateString();
		}
	}
