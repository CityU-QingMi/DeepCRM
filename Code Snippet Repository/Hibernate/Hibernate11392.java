	public boolean registerRemoteInvalidation(Object key, Object lockOwner) {
		SharedSessionContractImplementor session = currentSession.get();
		TransactionCoordinator transactionCoordinator = session == null ? null : session.getTransactionCoordinator();
		if (transactionCoordinator != null) {
			if (trace) {
				log.tracef("Registering synchronization on transaction in %s, cache %s: %s", lockOwnerToString(session), cache.getName(), key);
			}
			InvalidationSynchronization sync = new InvalidationSynchronization(nonTxPutFromLoadInterceptor, key, lockOwner);
			transactionCoordinator.getLocalSynchronizations().registerSynchronization(sync);
			return true;
		}
		// evict() command is not executed in session context
		return false;
	}
