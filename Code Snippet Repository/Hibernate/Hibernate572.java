	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder( getClass().getSimpleName() )
				.append( '[' );
		for ( Map.Entry<AbstractEntityInsertAction,NonNullableTransientDependencies> entry : dependenciesByAction.entrySet() ) {
			final AbstractEntityInsertAction insert = entry.getKey();
			final NonNullableTransientDependencies dependencies = entry.getValue();
			sb.append( "[insert=" )
					.append( insert )
					.append( " dependencies=[" )
					.append( dependencies.toLoggableString( insert.getSession() ) )
					.append( "]" );
		}
		sb.append( ']');
		return sb.toString();
	}
