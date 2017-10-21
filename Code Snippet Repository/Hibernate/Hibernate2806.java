	@Override
	protected String applyLocks(
			String sql,
			QueryParameters parameters,
			Dialect dialect,
			List<AfterLoadAction> afterLoadActions) throws QueryException {
		// can't cache this stuff either (per-invocation)
		final LockOptions lockOptions = parameters.getLockOptions();
		final String result;
		if ( lockOptions == null ||
				( lockOptions.getLockMode() == LockMode.NONE && lockOptions.getAliasLockCount() == 0 ) ) {
			return sql;
		}
		else {
			LockOptions locks = new LockOptions();
			locks.setLockMode( lockOptions.getLockMode() );
			locks.setTimeOut( lockOptions.getTimeOut() );
			locks.setScope( lockOptions.getScope() );
			Iterator iter = lockOptions.getAliasLockIterator();
			while ( iter.hasNext() ) {
				Map.Entry me = (Map.Entry) iter.next();
				locks.setAliasSpecificLockMode( getAliasName( (String) me.getKey() ), (LockMode) me.getValue() );
			}
			Map keyColumnNames = null;
			if ( dialect.forUpdateOfColumns() ) {
				keyColumnNames = new HashMap();
				for ( int i = 0; i < names.length; i++ ) {
					keyColumnNames.put( names[i], persisters[i].getIdentifierColumnNames() );
				}
			}
			result = dialect.applyLocksToSql( sql, locks, keyColumnNames );
		}
		logQuery( queryString, result );
		return result;
	}
