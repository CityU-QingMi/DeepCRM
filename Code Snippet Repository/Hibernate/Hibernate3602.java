	@Override
	public void performDiscovery(JdbcResultMetadata metadata, List<Type> types, List<String> aliases) throws SQLException {
		final List<Type> localTypes = new ArrayList<>();
		for ( ScalarResultColumnProcessor scalar : scalarProcessors ) {
			scalar.performDiscovery( metadata, localTypes, aliases );
		}

		types.addAll( localTypes );

		constructor = resolveConstructor( targetClass, localTypes );
	}
