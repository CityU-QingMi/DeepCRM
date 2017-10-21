	public synchronized void registerNamedSQLQueryDefinition(String name, NamedSQLQueryDefinition definition) {
		if ( ! name.equals( definition.getName() ) ) {
			definition = definition.makeCopy( name );
		}

		final Map<String, NamedSQLQueryDefinition> copy = CollectionHelper.makeCopy( namedSqlQueryDefinitionMap );
		final NamedQueryDefinition previous = copy.put( name, definition );
		if ( previous != null ) {
			log.debugf(
					"registering named SQL query definition [%s] overriding previously registered definition [%s]",
					name,
					previous
			);
		}

		this.namedSqlQueryDefinitionMap = Collections.unmodifiableMap( copy );
	}
