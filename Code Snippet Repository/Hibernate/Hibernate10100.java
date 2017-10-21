	public AuditProcess get(EventSource session) {
		final Transaction transaction = session.accessTransaction();

		AuditProcess auditProcess = auditProcesses.get( transaction );
		if ( auditProcess == null ) {
			// No worries about registering a transaction twice - a transaction is single thread
			auditProcess = new AuditProcess( revisionInfoGenerator, session );
			auditProcesses.put( transaction, auditProcess );

			session.getActionQueue().registerProcess(
					new BeforeTransactionCompletionProcess() {
						public void doBeforeTransactionCompletion(SessionImplementor session) {
							final AuditProcess process = auditProcesses.get( transaction );
							if ( process != null ) {
								process.doBeforeTransactionCompletion( session );
							}
						}
					}
			);

			session.getActionQueue().registerProcess(
					new AfterTransactionCompletionProcess() {
						public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) {
							auditProcesses.remove( transaction );
						}
					}
			);
		}

		return auditProcess;
	}
