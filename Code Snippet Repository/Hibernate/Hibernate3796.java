	protected void applyRootReturnTableFragments(SelectStatementBuilder select) {
		final String fromTableFragment;
		final String rootAlias = entityReferenceAliases.getTableAlias();
		final OuterJoinLoadable outerJoinLoadable = (OuterJoinLoadable) getRootEntityReturn().getEntityPersister();
		final Dialect dialect = getSessionFactory().getJdbcServices().getJdbcEnvironment().getDialect();
		if ( getQueryBuildingParameters().getLockOptions() != null ) {
			fromTableFragment = dialect.appendLockHint(
					getQueryBuildingParameters().getLockOptions(),
					outerJoinLoadable.fromTableFragment( rootAlias )
			);
			select.setLockOptions( getQueryBuildingParameters().getLockOptions() );
		}
		else if ( getQueryBuildingParameters().getLockMode() != null ) {
			fromTableFragment = dialect.appendLockHint(
					getQueryBuildingParameters().getLockMode(),
					outerJoinLoadable.fromTableFragment( rootAlias )
			);
			select.setLockMode( getQueryBuildingParameters().getLockMode() );
		}
		else {
			fromTableFragment = outerJoinLoadable.fromTableFragment( rootAlias );
		}
		select.appendFromClauseFragment( fromTableFragment + outerJoinLoadable.fromJoinFragment( rootAlias, true, true ) );
	}
