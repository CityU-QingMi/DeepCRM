	public void serialize(ObjectOutputStream oos) throws IOException {
		LOG.trace( "Serializing action-queue" );
		if( unresolvedInsertions == null ) {
			unresolvedInsertions = new UnresolvedEntityInsertActions();
		}
		unresolvedInsertions.serialize( oos );

		for ( ListProvider p : EXECUTABLE_LISTS_MAP.values() ) {
			ExecutableList<?> l = p.get( this );
			if( l == null ) {
				oos.writeBoolean( false );
			}
			else {
				oos.writeBoolean( true );
				l.writeExternal( oos );
			}
		}
	}
