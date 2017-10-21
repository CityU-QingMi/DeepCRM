	@SuppressWarnings("")
	public static Object increment(Object version, VersionType versionType, SharedSessionContractImplementor session) {
		final Object next = versionType.next( version, session );
		if ( LOG.isTraceEnabled() ) {
			LOG.tracef(
					"Incrementing: %s to %s",
					versionType.toLoggableString( version, session.getFactory() ),
					versionType.toLoggableString( next, session.getFactory() )
			);
		}
		return next;
	}
