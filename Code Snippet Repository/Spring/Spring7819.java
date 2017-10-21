	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		JpaTransactionObject txObject = (JpaTransactionObject) transaction;

		// Remove the entity manager holder from the thread, if still there.
		// (Could have been removed by EntityManagerFactoryUtils in order
		// to replace it with an unsynchronized EntityManager).
		if (txObject.isNewEntityManagerHolder()) {
			TransactionSynchronizationManager.unbindResourceIfPossible(obtainEntityManagerFactory());
		}
		txObject.getEntityManagerHolder().clear();

		// Remove the JDBC connection holder from the thread, if exposed.
		if (getDataSource() != null && txObject.hasConnectionHolder()) {
			TransactionSynchronizationManager.unbindResource(getDataSource());
			ConnectionHandle conHandle = txObject.getConnectionHolder().getConnectionHandle();
			if (conHandle != null) {
				try {
					getJpaDialect().releaseJdbcConnection(conHandle,
							txObject.getEntityManagerHolder().getEntityManager());
				}
				catch (Exception ex) {
					// Just log it, to keep a transaction-related exception.
					logger.error("Could not close JDBC connection after transaction", ex);
				}
			}
		}

		getJpaDialect().cleanupTransaction(txObject.getTransactionData());

		// Remove the entity manager holder from the thread.
		if (txObject.isNewEntityManagerHolder()) {
			EntityManager em = txObject.getEntityManagerHolder().getEntityManager();
			if (logger.isDebugEnabled()) {
				logger.debug("Closing JPA EntityManager [" + em + "] after transaction");
			}
			EntityManagerFactoryUtils.closeEntityManager(em);
		}
		else {
			logger.debug("Not closing pre-bound JPA EntityManager after transaction");
		}
	}
