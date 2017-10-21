	@Override
	public void rollback() {
		// todo : may need a "JPA compliant" flag here

		TransactionStatus status = getStatus();
		if ( status == TransactionStatus.ROLLED_BACK || status == TransactionStatus.NOT_ACTIVE ) {
			// Allow rollback() calls on completed transactions, just no-op.
			LOG.debug( "rollback() called on an inactive transaction" );
			return;
		}

		if ( !status.canRollback() ) {
			throw new TransactionException( "Cannot rollback transaction in current status [" + status.name() + "]" );
		}

		LOG.debug( "rolling back" );
		if ( status != TransactionStatus.FAILED_COMMIT || allowFailedCommitToPhysicallyRollback() ) {
			internalGetTransactionDriverControl().rollback();
		}
	}
