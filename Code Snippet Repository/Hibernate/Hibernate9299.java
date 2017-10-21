	public void reset() throws SystemException {
		if ( transactionManager != null ) {
			if ( transactionManager.getStatus() != Status.STATUS_NO_TRANSACTION ) {
				transactionManager.rollback();
			}
		}
		if ( userTransaction != null ) {
			if ( userTransaction.getStatus() != Status.STATUS_NO_TRANSACTION ) {
				userTransaction.rollback();
			}
		}
	}
