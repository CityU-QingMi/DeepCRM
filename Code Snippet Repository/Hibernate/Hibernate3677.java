	@Override
	protected Object getResultColumnOrRow(
			Object[] row,
			ResultTransformer transformer,
			ResultSet rs,
			SharedSessionContractImplementor session)
			throws SQLException, HibernateException {

		Object[] resultRow = getResultRow( row, rs, session );
		boolean hasTransform = hasSelectNew() || transformer != null;
		return ( !hasTransform && resultRow.length == 1 ?
				resultRow[0] :
				resultRow
		);
	}
