	public Object[] buildResultRow(Object[] data, ResultSet resultSet, SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		Object[] resultRow;
		if ( !hasScalars ) {
			resultRow = data;
		}
		else {
			// build an array with indices equal to the total number
			// of actual returns in the result Hibernate will return
			// for this query (scalars + non-scalars)
			resultRow = new Object[ columnProcessors.length ];
			for ( int i = 0; i < columnProcessors.length; i++ ) {
				resultRow[i] = columnProcessors[i].extract( data, resultSet, session );
			}
		}

		return resultRow;
	}
