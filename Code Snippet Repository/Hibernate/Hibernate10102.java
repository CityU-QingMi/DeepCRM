	public void scheduleAuditDataRemoval(final Session session, final Object data) {
		( (EventSource) session ).getActionQueue().registerProcess(
				new AfterTransactionCompletionProcess() {
					public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor sessionImplementor) {
						if ( !sessionImplementor.isClosed() ) {
							try {
								( (Session) sessionImplementor ).evict( data );
							}
							catch ( HibernateException ignore ) {
							}
						}
					}
				}
		);
	}
