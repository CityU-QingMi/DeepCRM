	protected final void initProjection(
			final String projectionString,
			final String whereString,
			final String orderByString,
			final String groupByString,
			final LockOptions lockOptions) throws MappingException {
		walkEntityTree( persister, getAlias() );
		persisters = new Loadable[0];
		initStatementString(projectionString, whereString, orderByString, groupByString, lockOptions);
	}
