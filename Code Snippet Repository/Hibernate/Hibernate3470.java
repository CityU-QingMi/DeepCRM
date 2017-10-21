	public static BatchFetchStyle interpret(Object setting) {
		log.tracef( "Interpreting BatchFetchStyle from setting : %s", setting );

		if ( setting == null ) {
			return LEGACY; // as default
		}

		if ( BatchFetchStyle.class.isInstance( setting ) ) {
			return (BatchFetchStyle) setting;
		}

		try {
			final BatchFetchStyle byName = byName( setting.toString() );
			if ( byName != null ) {
				return byName;
			}
		}
		catch (Exception ignore) {
		}

		log.debugf( "Unable to interpret given setting [%s] as BatchFetchStyle", setting );

		return LEGACY; // again as default.
	}
