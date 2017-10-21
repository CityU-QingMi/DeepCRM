	@Override
	public Object visitPrepareCommand(TxInvocationContext ctx, PrepareCommand command) throws Throwable {
		if (ctx.isOriginLocal()) {
			// We can't wait to commit phase to remove the entry locally (invalidations are processed in 1pc
			// on remote nodes, so only local case matters here). The problem is that while the entry is locked
			// reads still can take place and we can read outdated collection after reading updated entity
			// owning this collection from DB; when this happens, the version lock on entity cannot protect
			// us against concurrent modification of the collection. Therefore, we need to remove the entry
			// here (even without lock!) and let possible update happen in commit phase.
			for (WriteCommand wc : command.getModifications()) {
				for (Object key : wc.getAffectedKeys()) {
					dataContainer.remove(key);
				}
			}
		}
		else {
			for (WriteCommand wc : command.getModifications()) {
				Set<Object> keys = wc.getAffectedKeys();
				if (log.isTraceEnabled()) {
					log.tracef("Invalidating keys %s with lock owner %s", keys, ctx.getLockOwner());
				}
				for (Object key : keys ) {
					putFromLoadValidator.beginInvalidatingKey(ctx.getLockOwner(), key);
				}
			}
		}
		return invokeNextInterceptor(ctx, command);
	}
