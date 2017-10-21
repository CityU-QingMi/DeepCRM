	private <T> T doInNewTransaction(HibernateCallable<T> callable, TransactionManager transactionManager) {
		try {
			// start the new isolated transaction
			transactionManager.begin();

			try {
				T result = callable.call();
				// if everything went ok, commit the isolated transaction
				transactionManager.commit();
				return result;
			}
			catch (Exception e) {
				try {
					transactionManager.rollback();
				}
				catch (Exception ignore) {
					LOG.unableToRollbackIsolatedTransaction( e, ignore );
				}
				throw new HibernateException( "Could not apply work", e );
			}
		}
		catch (SystemException e) {
			throw new HibernateException( "Unable to start isolated transaction", e );
		}
		catch (NotSupportedException e) {
			throw new HibernateException( "Unable to start isolated transaction", e );
		}
	}
