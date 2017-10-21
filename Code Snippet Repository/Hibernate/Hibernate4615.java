	@Override
	public void beforeCompletion() {
		try {
			transactionCoordinatorOwner.beforeTransactionCompletion();
		}
		catch (HibernateException e) {
			physicalTransactionDelegate.markRollbackOnly();
			throw e;
		}
		catch (RuntimeException re) {
			physicalTransactionDelegate.markRollbackOnly();
			throw re;
		}
		finally {
			synchronizationRegistry.notifySynchronizationsBeforeTransactionCompletion();
			for ( TransactionObserver observer : observers() ) {
				observer.beforeCompletion();
			}
		}
	}
