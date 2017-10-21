	@Override
	public TransactionDriver getTransactionDriverControl() {
		// Again, this PhysicalTransactionDelegate will act as the bridge from the local transaction back into the
		// coordinator.  We lazily build it as we invalidate each delegate after each transaction (a delegate is
		// valid for just one transaction)
		if ( physicalTransactionDelegate == null ) {
			physicalTransactionDelegate = new TransactionDriverControlImpl( jdbcResourceTransactionAccess.getResourceLocalTransaction() );
		}
		return physicalTransactionDelegate;
	}
