	protected <T> T withTx(NodeEnvironment environment, SharedSessionContractImplementor session, Callable<T> callable) throws Exception {
		if (jtaPlatform != null) {
			TransactionManager tm = environment.getServiceRegistry().getService(JtaPlatform.class).retrieveTransactionManager();
			return Caches.withinTx(tm, callable);
		} else {
			Transaction transaction = ((Session) session).beginTransaction();
			boolean rollingBack = false;
			try {
				T retval = callable.call();
				if (transaction.getStatus() == TransactionStatus.ACTIVE) {
					transaction.commit();
				} else {
					rollingBack = true;
					transaction.rollback();
				}
				return retval;
			} catch (Exception e) {
				if (!rollingBack) {
					try {
						transaction.rollback();
					} catch (Exception suppressed) {
						e.addSuppressed(suppressed);
					}
				}
				throw e;
			}
		}
	}
