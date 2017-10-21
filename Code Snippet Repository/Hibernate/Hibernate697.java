	@Override
	public void addNamedQuery(NamedQueryDefinition def) {
		if ( def == null ) {
			throw new IllegalArgumentException( "Named query definition is null" );
		}
		else if ( def.getName() == null ) {
			throw new IllegalArgumentException( "Named query definition name is null: " + def.getQueryString() );
		}

		if ( defaultNamedQueryNames.contains( def.getName() ) ) {
			return;
		}

		applyNamedQuery( def.getName(), def );
	}
