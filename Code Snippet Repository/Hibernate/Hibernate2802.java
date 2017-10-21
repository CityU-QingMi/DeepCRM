	@Override
	protected Object getResultColumnOrRow(
			Object[] row,
			ResultTransformer transformer,
			ResultSet rs,
			SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		Object[] resultRow = getResultRow( row, rs, session );
		return ( holderClass == null && resultRow.length == 1 ?
				resultRow[0] :
				resultRow
		);
	}
