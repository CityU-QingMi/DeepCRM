	protected TransactionSynchronizationRegistry lookupTransactionSynchronizationRegistry(String registryName) throws TransactionSystemException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Retrieving JTA TransactionSynchronizationRegistry from JNDI location [" + registryName + "]");
			}
			return getJndiTemplate().lookup(registryName, TransactionSynchronizationRegistry.class);
		}
		catch (NamingException ex) {
			throw new TransactionSystemException(
					"JTA TransactionSynchronizationRegistry is not available at JNDI location [" + registryName + "]", ex);
		}
	}
