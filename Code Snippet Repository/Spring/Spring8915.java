	@Nullable
	protected TransactionSynchronizationRegistry findTransactionSynchronizationRegistry(
			@Nullable UserTransaction ut, @Nullable TransactionManager tm) throws TransactionSystemException {

		if (this.userTransactionObtainedFromJndi) {
			// UserTransaction has already been obtained from JNDI, so the
			// TransactionSynchronizationRegistry probably sits there as well.
			String jndiName = DEFAULT_TRANSACTION_SYNCHRONIZATION_REGISTRY_NAME;
			try {
				TransactionSynchronizationRegistry tsr = getJndiTemplate().lookup(jndiName, TransactionSynchronizationRegistry.class);
				if (logger.isDebugEnabled()) {
					logger.debug("JTA TransactionSynchronizationRegistry found at default JNDI location [" + jndiName + "]");
				}
				return tsr;
			}
			catch (NamingException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("No JTA TransactionSynchronizationRegistry found at default JNDI location [" + jndiName + "]", ex);
				}
			}
		}
		// Check whether the UserTransaction or TransactionManager implements it...
		if (ut instanceof TransactionSynchronizationRegistry) {
			return (TransactionSynchronizationRegistry) ut;
		}
		if (tm instanceof TransactionSynchronizationRegistry) {
			return (TransactionSynchronizationRegistry) tm;
		}
		// OK, so no JTA 1.1 TransactionSynchronizationRegistry is available...
		return null;
	}
