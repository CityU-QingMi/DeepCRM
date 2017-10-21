	public void addJoin(String tableName, String alias, String[] fkColumns, String[] pkColumns, JoinType joinType) {
		addCrossJoin( tableName, alias );

		for ( int j = 0; j < fkColumns.length; j++ ) {
			setHasThetaJoins( true );
			afterWhere.append( " and " )
					.append( fkColumns[j] );
			if ( joinType == JoinType.RIGHT_OUTER_JOIN || joinType == JoinType.FULL_JOIN ) {
				afterWhere.append( "(+)" );
			}
			afterWhere.append( '=' )
					.append( alias )
					.append( '.' )
					.append( pkColumns[j] );
			if ( joinType == JoinType.LEFT_OUTER_JOIN || joinType == JoinType.FULL_JOIN ) {
				afterWhere.append( "(+)" );
			}
		}
	}
