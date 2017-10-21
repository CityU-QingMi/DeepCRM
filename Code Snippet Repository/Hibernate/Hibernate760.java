	@SuppressWarnings("")
	public boolean toBoolean(boolean defaultValue) {
		if ( this == TRUE ) {
			return true;
		}
		else if ( this == FALSE ) {
			return false;
		}
		else {
			return defaultValue;
		}
	}
