	@Override
	public void setTimeOut(int seconds) {
		if ( seconds > 0 ) {
			try {
				userTransaction.setTransactionTimeout( seconds );
			}
			catch (SystemException e) {
				throw new TransactionException( "Unable to apply requested transaction timeout", e );
			}
		}
	}
