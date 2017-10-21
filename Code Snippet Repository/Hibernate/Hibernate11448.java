	private void replaceExpirationManager() {
		// ClusteredExpirationManager sends RemoteExpirationCommands to remote nodes which causes
		// undesired overhead. When get() triggers a RemoteExpirationCommand executed in async executor
		// this locks the entry for the duration of RPC, and putFromLoad with ZERO_LOCK_ACQUISITION_TIMEOUT
		// fails as it finds the entry being blocked.
		ExpirationManager expirationManager = cache.getComponentRegistry().getComponent(ExpirationManager.class);
		if ((expirationManager instanceof ClusterExpirationManager)) {
			// re-registering component does not stop the old one
			((ClusterExpirationManager) expirationManager).stop();
			cache.getComponentRegistry().registerComponent(new ExpirationManagerImpl<>(), ExpirationManager.class);
			cache.getComponentRegistry().rewire();
		}
		else if (expirationManager instanceof ExpirationManagerImpl) {
			// do nothing
		}
		else {
			throw new IllegalStateException("Expected clustered expiration manager, found " + expirationManager);
		}
	}
