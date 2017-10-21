	@Override
	public void initializeReplicableCommand(ReplicableCommand c, boolean isRemote) {
		switch (c.getCommandId()) {
			case CacheCommandIds.END_INVALIDATION:
				EndInvalidationCommand endInvalidationCommand = (EndInvalidationCommand) c;
				endInvalidationCommand.setPutFromLoadValidator(putFromLoadValidators.get(endInvalidationCommand.getCacheName()));
				break;
			case CacheCommandIds.BEGIN_INVALIDATION:
				BeginInvalidationCommand beginInvalidationCommand = (BeginInvalidationCommand) c;
				beginInvalidationCommand.init(notifier, configuration);
				break;
		}
	}
