	public Object buildResultRow(Object[] data, ResultSet resultSet, boolean hasTransformer, SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		final Object[] resultRow = buildResultRow( data, resultSet, session );
		if ( hasTransformer ) {
			return resultRow;
		}
		else {
			return resultRow.length == 1
					? resultRow[0]
					: resultRow;
		}
	}
