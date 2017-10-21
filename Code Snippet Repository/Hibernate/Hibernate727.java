	public static String toXml(FlushMode mode) {
		if ( mode == null ) {
			return null;
		}

		// conversely, we want to map MANUAL -> "never" here
		if ( mode == FlushMode.MANUAL ) {
			return "never";
		}

		// todo : what to do if the incoming value does not conform to allowed values?
		// for now, we simply don't deal with that (we write it out).

		return mode.name().toLowerCase( Locale.ENGLISH );
	}
