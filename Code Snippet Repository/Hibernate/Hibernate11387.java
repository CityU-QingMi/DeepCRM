	@Override
	public Object visitPutKeyValueCommand(InvocationContext ctx, PutKeyValueCommand command) throws Throwable {
		if (command.hasFlag(Flag.PUT_FOR_EXTERNAL_READ)) {
			return invokeNextInterceptor(ctx, command);
		}
		else {
			boolean isTransactional = putFromLoadValidator.registerRemoteInvalidation(command.getKey(), command.getKeyLockOwner());
			if (!isTransactional) {
				throw new IllegalStateException("Put executed without transaction!");
			}
			if (!putFromLoadValidator.beginInvalidatingWithPFER(command.getKeyLockOwner(), command.getKey(), command.getValue())) {
				log.failedInvalidatePendingPut(command.getKey(), cacheName);
			}
			RemoveCommand removeCommand = commandsFactory.buildRemoveCommand(command.getKey(), null, command.getFlags());
			Object retval = invokeNextInterceptor(ctx, removeCommand);
			if (command.isSuccessful()) {
				invalidateAcrossCluster(command, isTransactional, command.getKey());
			}
			return retval;
		}
	}
