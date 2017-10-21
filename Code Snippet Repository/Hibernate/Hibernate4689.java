	public void addJoin(String tableName, String alias, String[] fkColumns, String[] pkColumns, JoinType joinType) {

		addCrossJoin( tableName, alias );

		for ( int j = 0; j < fkColumns.length; j++ ) {
			//full joins are not supported.. yet!
			if ( joinType == JoinType.FULL_JOIN ) {
				throw new UnsupportedOperationException();
			}

			afterWhere.append( " and " )
					.append( fkColumns[j] )
					.append( " " );

			if ( joinType == JoinType.LEFT_OUTER_JOIN ) {
				afterWhere.append( '*' );
			}
			afterWhere.append( '=' );
			if ( joinType == JoinType.RIGHT_OUTER_JOIN ) {
				afterWhere.append( "*" );
			}

			afterWhere.append( " " )
					.append( alias )
					.append( '.' )
					.append( pkColumns[j] );
		}
	}
