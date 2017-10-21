	private <T> T doInSuspendedTransaction(HibernateCallable<T> callable) {
		try {
			// First we suspend any current JTA transaction
			Transaction surroundingTransaction = transactionManager.suspend();
			LOG.debugf( "Surrounding JTA transaction suspended [%s]", surroundingTransaction );

			boolean hadProblems = false;
			try {
				return callable.call();
			}
			catch (HibernateException e) {
				hadProblems = true;
				throw e;
			}
			finally {
				try {
					transactionManager.resume( surroundingTransaction );
					LOG.debugf( "Surrounding JTA transaction resumed [%s]", surroundingTransaction );
				}
				catch (Throwable t) {
					// if the actually work had an error use that, otherwise error based on t
					if ( !hadProblems ) {
						//noinspection ThrowFromFinallyBlock
						throw new HibernateException( "Unable to resume previously suspended transaction", t );
					}
				}
			}
		}
		catch (SystemException e) {
			throw new HibernateException( "Unable to suspend current JTA transaction", e );
		}
	}
