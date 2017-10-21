	@Override
	public void beforeCompletion() {
		try {
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			this.springSynchronization.beforeCommit(readOnly);
		}
		catch (RuntimeException ex) {
			setRollbackOnlyIfPossible();
			throw ex;
		}
		catch (Error err) {
			setRollbackOnlyIfPossible();
			throw err;
		}
		finally {
			// Process Spring's beforeCompletion early, in order to avoid issues
			// with strict JTA implementations that issue warnings when doing JDBC
			// operations after transaction completion (e.g. Connection.getWarnings).
			this.beforeCompletionCalled = true;
			this.springSynchronization.beforeCompletion();
		}
	}
