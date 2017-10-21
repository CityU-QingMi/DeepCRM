	@Override
	public TransactionCoordinator buildTransactionCoordinator(TransactionCoordinatorOwner owner, Options options) {
		if ( owner instanceof JdbcResourceTransactionAccess ) {
			return new JdbcResourceLocalTransactionCoordinatorImpl( this, owner, (JdbcResourceTransactionAccess) owner );
		}

		throw new HibernateException(
				"Could not determine ResourceLocalTransactionAccess to use in building TransactionCoordinator"
		);
	}
