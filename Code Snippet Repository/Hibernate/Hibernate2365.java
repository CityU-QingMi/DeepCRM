	public static int getStatus(TransactionManager transactionManager) {
		try {
			final int status = transactionManager.getStatus();
			if ( status == Status.STATUS_UNKNOWN ) {
				throw new TransactionException( "TransactionManager reported transaction status as unknwon" );
			}
			return status;
		}
		catch ( SystemException se ) {
			throw new TransactionException( "Could not determine transaction status", se );
		}
	}
