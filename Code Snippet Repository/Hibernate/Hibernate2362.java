	@Override
	public void commit() {
		if ( !isActive() ) {
			// allow MARKED_ROLLBACK to propagate through to transactionDriverControl
			throw new IllegalStateException( "Transaction not successfully started" );
		}

		LOG.debug( "committing" );
		try {
			internalGetTransactionDriverControl().commit();
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convertCommitException( e );
		}
	}
