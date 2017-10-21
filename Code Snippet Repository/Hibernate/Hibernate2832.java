	protected Select generateIdSelect(
			String tableAlias,
			ProcessedWhereClause whereClause) {

		final Dialect dialect = sessionFactory.getJdbcServices().getJdbcEnvironment().getDialect();

		final Select select = new Select( dialect );
		final SelectValues selectClause = new SelectValues( dialect ).addColumns(
				tableAlias,
				getTargetedQueryable().getIdentifierColumnNames(),
				getTargetedQueryable().getIdentifierColumnNames()
		);
		addAnyExtraIdSelectValues( selectClause );
		select.setSelectClause( selectClause.render() );

		String rootTableName = getTargetedQueryable().getTableName();
		String fromJoinFragment = getTargetedQueryable().fromJoinFragment( tableAlias, true, false );
		String whereJoinFragment = getTargetedQueryable().whereJoinFragment( tableAlias, true, false );

		select.setFromClause( rootTableName + ' ' + tableAlias + fromJoinFragment );

		if ( whereJoinFragment == null ) {
			whereJoinFragment = "";
		}
		else {
			whereJoinFragment = whereJoinFragment.trim();
			if ( whereJoinFragment.startsWith( "and" ) ) {
				whereJoinFragment = whereJoinFragment.substring( 4 );
			}
		}

		if ( whereClause.getUserWhereClauseFragment().length() > 0 ) {
			if ( whereJoinFragment.length() > 0 ) {
				whereJoinFragment += " and ";
			}
		}
		select.setWhereClause( whereJoinFragment + whereClause.getUserWhereClauseFragment() );
		return select;
	}
