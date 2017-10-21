	@Override
	public Object visitRemoveCommand(InvocationContext ctx, RemoveCommand command) throws Throwable {
		boolean isTransactional = putFromLoadValidator.registerRemoteInvalidation(command.getKey(), command.getKeyLockOwner());
		if (isTransactional) {
			if (!putFromLoadValidator.beginInvalidatingKey(command.getKeyLockOwner(), command.getKey())) {
				log.failedInvalidatePendingPut(command.getKey(), cacheName);
			}
		}
		else {
			log.trace("This is an eviction, not invalidating anything");
		}
		Object retval = invokeNextInterceptor(ctx, command);
		if (command.isSuccessful()) {
			invalidateAcrossCluster(command, isTransactional, command.getKey());
		}
		return retval;
	}
