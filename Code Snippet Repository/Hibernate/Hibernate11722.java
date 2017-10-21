	@Override
	public SynchronizationRegistry getLocalSynchronizations() {
		return new SynchronizationRegistry() {
			@Override
			public void registerSynchronization(Synchronization synchronization) {
				try {
					BatchModeTransactionManager.getInstance().getTransaction().registerSynchronization(synchronization);
				} catch (RollbackException e) {
					throw new RuntimeException(e);
				} catch (SystemException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
