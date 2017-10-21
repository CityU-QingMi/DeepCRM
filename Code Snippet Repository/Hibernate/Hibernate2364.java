	public static int getStatus(UserTransaction userTransaction) {
		try {
			final int status = userTransaction.getStatus();
			if ( status == Status.STATUS_UNKNOWN ) {
				throw new TransactionException( "UserTransaction reported transaction status as unknown" );
			}
			return status;
		}
		catch ( SystemException se ) {
			throw new TransactionException( "Could not determine transaction status", se );
		}
	}
