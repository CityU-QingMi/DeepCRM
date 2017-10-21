	@SuppressWarnings("")
	protected List<R> doList() {
		if ( getMaxResults() == 0 ) {
			return Collections.EMPTY_LIST;
		}
		if ( lockOptions.getLockMode() != null && lockOptions.getLockMode() != LockMode.NONE ) {
			if ( !getProducer().isTransactionInProgress() ) {
				throw new TransactionRequiredException( "no transaction is in progress" );
			}
		}

		return getProducer().list(
				queryParameterBindings.expandListValuedParameters( getQueryString(), getProducer() ),
				getQueryParameters()
		);
	}
