	public static String toIsolationNiceName(Integer isolation) {
		String name = null;

		if ( isolation != null ) {
			name = ISOLATION_VALUE_NICE_NAME_MAP.get( isolation );
		}

		if ( name == null ) {
			name = "<unknown>";
		}
		return name;
	}
