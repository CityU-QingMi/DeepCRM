	protected void doInitialize(
			FromClause fromClause,
			String tableAlias,
			String className,
			String classAlias,
			EntityPersister persister,
			EntityType type) {
		if ( initialized ) {
			throw new IllegalStateException( "Already initialized!!" );
		}
		this.fromClause = fromClause;
		this.tableAlias = tableAlias;
		this.className = className;
		this.classAlias = classAlias;
		this.elementType = new FromElementType( this, persister, type );
		// Register the FromElement with the FROM clause, now that we have the names and aliases.
		fromClause.registerFromElement( this );
		LOG.debugf( "%s : %s (%s) -> %s", fromClause, className, classAlias == null ? "<no alias>" : classAlias, tableAlias );
	}
