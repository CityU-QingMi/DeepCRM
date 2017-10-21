	protected boolean shouldUseFollowOnLocking(
			QueryParameters parameters,
			Dialect dialect,
			List<AfterLoadAction> afterLoadActions) {
		if ( ( parameters.getLockOptions().getFollowOnLocking() == null && dialect.useFollowOnLocking( parameters ) ) ||
				( parameters.getLockOptions().getFollowOnLocking() != null && parameters.getLockOptions().getFollowOnLocking() ) ) {
			// currently only one lock mode is allowed in follow-on locking
			final LockMode lockMode = determineFollowOnLockMode( parameters.getLockOptions() );
			final LockOptions lockOptions = new LockOptions( lockMode );
			if ( lockOptions.getLockMode() != LockMode.UPGRADE_SKIPLOCKED ) {
				if ( lockOptions.getLockMode() != LockMode.NONE ) {
					LOG.usingFollowOnLocking();
				}
				lockOptions.setTimeOut( parameters.getLockOptions().getTimeOut() );
				lockOptions.setScope( parameters.getLockOptions().getScope() );
				afterLoadActions.add(
						new AfterLoadAction() {
							@Override
							public void afterLoad(SharedSessionContractImplementor session, Object entity, Loadable persister) {
								( (Session) session ).buildLockRequest( lockOptions ).lock(
										persister.getEntityName(),
										entity
								);
							}
						}
				);
				parameters.setLockOptions( new LockOptions() );
				return true;
			}
		}
		return false;
	}
