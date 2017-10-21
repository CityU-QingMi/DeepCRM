	public static FlushModeType getFlushModeType(FlushMode flushMode) {
		if ( flushMode == FlushMode.ALWAYS ) {
			log.debug( "Interpreting Hibernate FlushMode#ALWAYS to JPA FlushModeType#AUTO; may cause problems if relying on FlushMode#ALWAYS-specific behavior" );
			return FlushModeType.AUTO;
		}
		else if ( flushMode == FlushMode.MANUAL ) {
			log.debug( "Interpreting Hibernate FlushMode#MANUAL to JPA FlushModeType#COMMIT; may cause problems if relying on FlushMode#MANUAL-specific behavior" );
			return FlushModeType.COMMIT;
		}
		else if ( flushMode == FlushMode.COMMIT ) {
			return FlushModeType.COMMIT;
		}
		else if ( flushMode == FlushMode.AUTO ) {
			return FlushModeType.AUTO;
		}

		throw new AssertionFailure( "unhandled FlushMode " + flushMode );
	}
