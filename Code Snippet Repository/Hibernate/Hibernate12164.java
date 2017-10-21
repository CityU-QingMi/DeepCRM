	public AccessType getAccessType() {
		if ( explicitAccessType != null ) {
			return explicitAccessType;
		}
		else if ( defaultAccessType != null ) {
			return defaultAccessType;

		}
		else {
			return DEFAULT_ACCESS_TYPE;
		}
	}
