	@Override
	public void addNamedNativeQuery(NamedSQLQueryDefinition def) {
		if ( def == null ) {
			throw new IllegalArgumentException( "Named native query definition object is null" );
		}
		if ( def.getName() == null ) {
			throw new IllegalArgumentException( "Named native query definition name is null: " + def.getQueryString() );
		}

		if ( defaultNamedNativeQueryNames.contains( def.getName() ) ) {
			return;
		}

		applyNamedNativeQuery( def.getName(), def );
	}
