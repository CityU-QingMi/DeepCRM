	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
		checkOpen();
		try {
			final ProcedureCallMemento memento = getFactory().getNamedQueryRepository().getNamedProcedureCallMemento( name );
			if ( memento == null ) {
				throw new IllegalArgumentException( "No @NamedStoredProcedureQuery was found with that name : " + name );
			}
			return memento.makeProcedureCall( this );
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}
