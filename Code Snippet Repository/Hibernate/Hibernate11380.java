	@Override
	protected Object visitDataWriteCommand(InvocationContext ctx, DataWriteCommand command) throws Throwable {
		Object returnValue = null;
		try {
			// Clear any metadata; we'll set them as appropriate in TombstoneCallInterceptor
			command.setMetadata(null);

			lockAndRecord(ctx, command.getKey(), getLockTimeoutMillis(command));

			returnValue = invokeNextInterceptor(ctx, command);
			return returnValue;
		}
		catch (TimeoutException e) {
			if (!ctx.isOriginLocal() && command.hasFlag(Flag.ZERO_LOCK_ACQUISITION_TIMEOUT)) {
				// FAIL_SILENTLY flag is not replicated to remote nodes and zero acquisition timeouts cause
				// very noisy logs.
				if (trace) {
					log.tracef("Silently ignoring exception", e);
				}
				return null;
			}
			else {
				throw e;
			}
		}
		finally {
			lockManager.unlockAll(ctx);
			if (returnValue instanceof CompletableFuture) {
				try {
					((CompletableFuture) returnValue).join();
				}
				catch (CompletionException e) {
					throw e.getCause();
				}
			}
		}
	}
