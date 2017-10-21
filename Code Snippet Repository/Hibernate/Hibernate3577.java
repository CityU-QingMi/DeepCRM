	@Override
	protected Object getResultColumnOrRow(
			Object[] row,
			ResultTransformer transformer,
			ResultSet rs,
			SharedSessionContractImplementor session)
			throws SQLException, HibernateException {
		return resolveResultTransformer( transformer ).transformTuple(
				getResultRow( row, rs, session ),
				getResultRowAliases()
		);
	}
