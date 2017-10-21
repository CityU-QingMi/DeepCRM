	public void addJoin(
			String rhsTableName,
			String rhsAlias,
			String[] lhsColumns,
			String[] rhsColumns,
			JoinType joinType,
			String on) {
		final String joinString;
		switch (joinType) {
			case INNER_JOIN:
				joinString = " inner join ";
				break;
			case LEFT_OUTER_JOIN:
				joinString = " left outer join ";
				break;
			case RIGHT_OUTER_JOIN:
				joinString = " right outer join ";
				break;
			case FULL_JOIN:
				joinString = " full outer join ";
				break;
			default:
				throw new AssertionFailure("undefined join type");
		}

		this.buffer.append(joinString)
			.append(rhsTableName)
			.append(' ')
			.append(rhsAlias)
			.append(" on ");


		for ( int j=0; j<lhsColumns.length; j++) {
			this.buffer.append( lhsColumns[j] )
				.append('=')
				.append(rhsAlias)
				.append('.')
				.append( rhsColumns[j] );
			if ( j < lhsColumns.length-1 ) {
				this.buffer.append( " and " );
			}
		}

		addCondition( buffer, on );

	}
