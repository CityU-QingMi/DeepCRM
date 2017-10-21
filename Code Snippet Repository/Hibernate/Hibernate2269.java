	public static ActionQueue deserialize(ObjectInputStream ois, SessionImplementor session) throws IOException, ClassNotFoundException {
		final boolean traceEnabled = LOG.isTraceEnabled();
		if ( traceEnabled ) {
			LOG.trace( "Deserializing action-queue" );
		}
		ActionQueue rtn = new ActionQueue( session );

		rtn.unresolvedInsertions = UnresolvedEntityInsertActions.deserialize( ois, session );

		for ( ListProvider provider : EXECUTABLE_LISTS_MAP.values() ) {
			ExecutableList<?> l = provider.get( rtn );
			boolean notNull = ois.readBoolean();
			if( notNull ) {
				if(l == null) {
					l = provider.init( rtn );
				}
				l.readExternal( ois );

				if ( traceEnabled ) {
					LOG.tracev( "Deserialized [{0}] entries", l.size() );
				}
				l.afterDeserialize( session );
			}
		}

		return rtn;
	}
