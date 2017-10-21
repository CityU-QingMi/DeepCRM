	private void beginInvalidating(InvocationContext ctx, Object key) {
		TxInvocationContext txCtx = (TxInvocationContext) ctx;
		// make sure that the command is registered in the transaction
		txCtx.addAffectedKey(key);

		GlobalTransaction globalTransaction = txCtx.getGlobalTransaction();
		if (!putFromLoadValidator.beginInvalidatingKey(globalTransaction, key)) {
			log.failedInvalidatePendingPut(key, cacheName);
		}
	}
