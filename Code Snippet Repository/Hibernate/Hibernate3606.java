	@Override
	protected String applyLocks(
			String sql,
			QueryParameters parameters,
			Dialect dialect,
			List<AfterLoadAction> afterLoadActions) throws QueryException {
		final LockOptions lockOptions = parameters.getLockOptions();
		if ( lockOptions == null ||
				( lockOptions.getLockMode() == LockMode.NONE && lockOptions.getAliasLockCount() == 0 ) ) {
			return sql;
		}

		// user is request locking, lets see if we can apply locking directly to the SQL...

		// 		some dialects wont allow locking with paging...
		afterLoadActions.add(
				new AfterLoadAction() {
					private final LockOptions originalLockOptions = lockOptions.makeCopy();

					@Override
					public void afterLoad(SharedSessionContractImplementor session, Object entity, Loadable persister) {
						( (Session) session ).buildLockRequest( originalLockOptions ).lock(
								persister.getEntityName(),
								entity
						);
					}
				}
		);
		parameters.getLockOptions().setLockMode( LockMode.READ );

		return sql;
	}
