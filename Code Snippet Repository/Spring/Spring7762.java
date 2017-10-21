	@Override
	protected void doResume(@Nullable Object transaction, Object suspendedResources) {
		SessionFactory sessionFactory = obtainSessionFactory();

		SuspendedResourcesHolder resourcesHolder = (SuspendedResourcesHolder) suspendedResources;
		if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
			// From non-transactional code running in active transaction synchronization
			// -> can be safely removed, will be closed on transaction completion.
			TransactionSynchronizationManager.unbindResource(sessionFactory);
		}
		TransactionSynchronizationManager.bindResource(sessionFactory, resourcesHolder.getSessionHolder());
		if (getDataSource() != null && resourcesHolder.getConnectionHolder() != null) {
			TransactionSynchronizationManager.bindResource(getDataSource(), resourcesHolder.getConnectionHolder());
		}
	}
