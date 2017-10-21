	public static PhysicalConnectionHandlingMode interpret(
			ConnectionAcquisitionMode acquisitionMode,
			ConnectionReleaseMode releaseMode) {
		if ( acquisitionMode == IMMEDIATELY ) {
			if ( releaseMode != null && releaseMode != ON_CLOSE ) {
				throw new IllegalArgumentException(
						"Only ConnectionReleaseMode.ON_CLOSE can be used in combination with " +
								"ConnectionAcquisitionMode.IMMEDIATELY; but ConnectionReleaseMode." +
								releaseMode.name() + " was specified."
				);
			}
			return IMMEDIATE_ACQUISITION_AND_HOLD;
		}
		else {
			switch ( releaseMode ) {
				case AFTER_STATEMENT: {
					return DELAYED_ACQUISITION_AND_RELEASE_AFTER_STATEMENT;
				}
				case AFTER_TRANSACTION: {
					return DELAYED_ACQUISITION_AND_RELEASE_AFTER_TRANSACTION;
				}
				default: {
					return DELAYED_ACQUISITION_AND_HOLD;
				}
			}
		}
	}
