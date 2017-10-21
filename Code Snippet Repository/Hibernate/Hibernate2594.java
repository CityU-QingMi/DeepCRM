	@Override
	public int execute(QueryParameters parameters, SharedSessionContractImplementor session) throws HibernateException {
		return doExecute(
			parameters,
			session,
			session.getJdbcServices().getDialect()
					.addSqlHintOrComment(
						sql,
						parameters,
						session.getFactory().getSessionFactoryOptions().isCommentsEnabled()
					),
			parameterSpecifications
		);
	}
