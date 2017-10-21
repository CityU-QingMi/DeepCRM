	public static LockMode fromExternalForm(String externalForm) {
		if ( externalForm == null ) {
			return NONE;
		}

		for ( LockMode lockMode : LockMode.values() ) {
			if ( lockMode.externalForm.equalsIgnoreCase( externalForm ) ) {
				return lockMode;
			}
		}

		throw new IllegalArgumentException( "Unable to interpret LockMode reference from incoming external form : " + externalForm );
	}
