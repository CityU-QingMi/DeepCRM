	protected String preprocessSQL(
			String sql,
			QueryParameters parameters,
			SessionFactoryImplementor sessionFactory,
			List<AfterLoadAction> afterLoadActions) throws HibernateException {

		Dialect dialect = sessionFactory.getServiceRegistry().getService( JdbcServices.class ).getDialect();

		sql = applyLocks( sql, parameters, dialect, afterLoadActions );

		sql = dialect.addSqlHintOrComment(
			sql,
			parameters,
			sessionFactory.getSessionFactoryOptions().isCommentsEnabled()
		);

		return processDistinctKeyword( sql, parameters );
	}
