	public synchronized void registerNamedQueryDefinition(String name, NamedQueryDefinition definition) {
		if ( NamedSQLQueryDefinition.class.isInstance( definition ) ) {
			throw new IllegalArgumentException( "NamedSQLQueryDefinition instance incorrectly passed to registerNamedQueryDefinition" );
		}

		if ( ! name.equals( definition.getName() ) ) {
			definition = definition.makeCopy( name );
		}

		final Map<String, NamedQueryDefinition> copy = CollectionHelper.makeCopy( namedQueryDefinitionMap );
		final NamedQueryDefinition previous = copy.put( name, definition );
		if ( previous != null ) {
			log.debugf(
					"registering named query definition [%s] overriding previously registered definition [%s]",
					name,
					previous
			);
		}

		this.namedQueryDefinitionMap = Collections.unmodifiableMap( copy );
	}
