	private TransactionDriverControlImpl makePhysicalTransactionDelegate() {
		JtaTransactionAdapter adapter;

		if ( preferUserTransactions ) {
			adapter = makeUserTransactionAdapter();

			if ( adapter == null ) {
				log.debug( "Unable to access UserTransaction, attempting to use TransactionManager instead" );
				adapter = makeTransactionManagerAdapter();
			}
		}
		else {
			adapter = makeTransactionManagerAdapter();

			if ( adapter == null ) {
				log.debug( "Unable to access TransactionManager, attempting to use UserTransaction instead" );
				adapter = makeUserTransactionAdapter();
			}
		}

		if ( adapter == null ) {
			throw new JtaPlatformInaccessibleException(
					"Unable to access TransactionManager or UserTransaction to make physical transaction delegate"
			);
		}

		return new TransactionDriverControlImpl( adapter );
	}
