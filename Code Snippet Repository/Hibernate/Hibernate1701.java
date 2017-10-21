	@Override
	public String getWriteLockString(String aliases, int timeout) {
		if ( timeout > 0 ) {
			return getForUpdateString( aliases ) + " wait " + timeout;
		}
		else if ( timeout == 0 ) {
			return getForUpdateNowaitString( aliases );
		}
		else {
			return getForUpdateString( aliases );
		}
	}
