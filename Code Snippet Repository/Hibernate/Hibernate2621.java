	public ComponentJoin(
			FromClause fromClause,
			FromElement origin,
			String alias,
			String componentPath,
			CompositeType componentType) {
		super( fromClause, origin, alias );
		this.componentPath = componentPath;
		this.componentType = componentType;
		this.componentProperty = StringHelper.unqualify( componentPath );
		fromClause.addJoinByPathMap( componentPath, this );
		initializeComponentJoin( new ComponentFromElementType( this ) );

		this.columns = origin.getPropertyMapping( "" ).toColumns( getTableAlias(), componentProperty );
		StringBuilder buf = new StringBuilder();
		for ( int j = 0; j < columns.length; j++ ) {
			final String column = columns[j];
			if ( j > 0 ) {
				buf.append( ", " );
			}
			buf.append( column );
		}
		this.columnsFragment = buf.toString();
	}
