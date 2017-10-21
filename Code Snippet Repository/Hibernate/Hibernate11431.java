	protected Object endInvalidationAndInvokeNextInterceptor(TxInvocationContext<?> ctx, VisitableCommand command) throws Throwable {
		try {
			if (ctx.isOriginLocal()) {
				// We cannot use directly ctx.getAffectedKeys() and that includes keys from local-only operations.
				// During evictAll inside transaction this would cause unnecessary invalidate command
				if (!ctx.getModifications().isEmpty()) {
					Object[] keys = ctx.getModifications().stream()
						.flatMap(mod -> mod.getAffectedKeys().stream()).distinct().toArray();

					if (log.isTraceEnabled()) {
						log.tracef( "Sending end invalidation for keys %s asynchronously, modifications are %s",
							Arrays.toString(keys), ctx.getCacheTransaction().getModifications());
					}

					GlobalTransaction globalTransaction = ctx.getGlobalTransaction();
					EndInvalidationCommand commitCommand = cacheCommandInitializer.buildEndInvalidationCommand(
							cacheName, keys, globalTransaction);
					List<Address> members = stateTransferManager.getCacheTopology().getMembers();
					rpcManager.invokeRemotely(members, commitCommand, asyncUnordered);

					// If the transaction is not successful, *RegionAccessStrategy would not be called, therefore
					// we have to end invalidation from here manually (in successful case as well)
					for (Object key : keys) {
						putFromLoadValidator.endInvalidatingKey(globalTransaction, key);
					}
				}
			}
		}
		finally {
			return invokeNextInterceptor(ctx, command);
		}
	}
