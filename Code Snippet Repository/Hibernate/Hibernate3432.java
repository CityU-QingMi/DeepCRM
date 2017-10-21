	public static FlushMode getFlushMode(FlushModeType flushModeType) {
		if ( flushModeType == FlushModeType.AUTO ) {
			return FlushMode.AUTO;
		}
		else if ( flushModeType == FlushModeType.COMMIT ) {
			return FlushMode.COMMIT;
		}

		throw new AssertionFailure( "unhandled FlushModeType " + flushModeType );
	}
