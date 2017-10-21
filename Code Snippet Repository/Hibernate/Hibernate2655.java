	protected void appendDisplayText(StringBuilder buf) {
		buf.append( isImplied() ? (
				isImpliedInFromClause() ? "implied in FROM clause" : "implied" )
				: "explicit" );
		buf.append( "," ).append( isCollectionJoin() ? "collection join" : "not a collection join" );
		buf.append( "," ).append( fetch ? "fetch join" : "not a fetch join" );
		buf.append( "," ).append( isAllPropertyFetch ? "fetch all properties" : "fetch non-lazy properties" );
		buf.append( ",classAlias=" ).append( getClassAlias() );
		buf.append( ",role=" ).append( role );
		buf.append( ",tableName=" ).append( getTableName() );
		buf.append( ",tableAlias=" ).append( getTableAlias() );
		FromElement origin = getRealOrigin();
		buf.append( ",origin=" ).append( origin == null ? "null" : origin.getText() );
		buf.append( ",columns={" );
		if ( columns != null ) {
			for ( int i = 0; i < columns.length; i++ ) {
				buf.append( columns[i] );
				if ( i < columns.length ) {
					buf.append( " " );
				}
			}
		}
		buf.append( ",className=" ).append( className );
		buf.append( "}" );
	}
