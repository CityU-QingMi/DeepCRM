	private void rollbackOnException(PlatformTransactionManager manager, TransactionStatus status, Throwable ex) {
		logger.debug("Initiating transaction rollback on listener exception", ex);
		try {
			manager.rollback(status);
		}
		catch (RuntimeException ex2) {
			logger.error("Listener exception overridden by rollback exception", ex);
			throw ex2;
		}
		catch (Error err) {
			logger.error("Listener exception overridden by rollback error", ex);
			throw err;
		}
	}
