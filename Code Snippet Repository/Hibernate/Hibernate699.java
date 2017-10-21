	@Override
	public void addNamedProcedureCallDefinition(NamedProcedureCallDefinition definition) {
		if ( definition == null ) {
			throw new IllegalArgumentException( "Named query definition is null" );
		}

		final String name = definition.getRegisteredName();

		if ( defaultNamedProcedureNames.contains( name ) ) {
			return;
		}

		final NamedProcedureCallDefinition previous = namedProcedureCallMap.put( name, definition );
		if ( previous != null ) {
			throw new DuplicateMappingException( DuplicateMappingException.Type.PROCEDURE, name );
		}
	}
