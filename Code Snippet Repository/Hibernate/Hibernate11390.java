	public void endInvalidating(Object key, Object lockOwner, boolean successful) {
		assert lockOwner != null;
		if (!putFromLoadValidator.endInvalidatingKey(lockOwner, key, successful)) {
			log.failedEndInvalidating(key, cacheName);
		}

		EndInvalidationCommand endInvalidationCommand = commandInitializer.buildEndInvalidationCommand(
				cacheName, new Object[] { key }, lockOwner);
		List<Address> members = stateTransferManager.getCacheTopology().getMembers();
		rpcManager.invokeRemotely(members, endInvalidationCommand, asyncUnordered);
	}
