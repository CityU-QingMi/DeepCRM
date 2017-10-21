	public synchronized void compile(
			String collectionRole,
			Map replacements,
			boolean scalar) throws QueryException, MappingException {

		if ( !isCompiled() ) {
			addFromAssociation( "this", collectionRole );
			compile( replacements, scalar );
		}
	}
