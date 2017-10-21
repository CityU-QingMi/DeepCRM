	private static FlushMode getFlushMode(FlushModeType flushMode)  {
		switch(flushMode) {
			case AUTO:
				return FlushMode.AUTO;
			case COMMIT:
				return FlushMode.COMMIT;
			default:
				throw new AssertionFailure("Unknown FlushModeType: " + flushMode);
		}
	}
