	protected String generateIdInsertSelect(
			String tableAlias,
			IdTableInfo idTableInfo,
			ProcessedWhereClause whereClause) {

		final Dialect dialect = sessionFactory.getJdbcServices().getJdbcEnvironment().getDialect();
		final Select select = generateIdSelect( tableAlias, whereClause );

		InsertSelect insert = new InsertSelect( dialect );
		if ( sessionFactory.getSessionFactoryOptions().isCommentsEnabled() ) {
			insert.setComment( "insert-select for " + getTargetedQueryable().getEntityName() + " ids" );
		}
		insert.setTableName( idTableInfo.getQualifiedIdTableName() );
		insert.setSelect( select );
		return insert.toStatementString();
	}
