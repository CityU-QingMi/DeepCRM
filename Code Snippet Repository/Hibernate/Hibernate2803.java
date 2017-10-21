	@Override
	protected Object[] getResultRow(Object[] row, ResultSet rs, SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		Object[] resultRow;
		if ( hasScalars ) {
			String[][] scalarColumns = getColumnNames();
			int queryCols = returnTypes.length;
			resultRow = new Object[queryCols];
			for ( int i = 0; i < queryCols; i++ ) {
				resultRow[i] = returnTypes[i].nullSafeGet( rs, scalarColumns[i], session, null );
			}
		}
		else {
			resultRow = toResultRow( row );
		}
		return resultRow;
	}
