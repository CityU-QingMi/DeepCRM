		@Override
		public void commit() {
			try {
				if ( rollbackOnly ) {
					throw new TransactionException( "Transaction was marked for rollback only; cannot commit" );
				}

				JdbcResourceLocalTransactionCoordinatorImpl.this.beforeCompletionCallback();
				jdbcResourceTransaction.commit();
				JdbcResourceLocalTransactionCoordinatorImpl.this.afterCompletionCallback( true );
			}
			catch (RuntimeException e) {
				try {
					rollback();
				}
				catch (RuntimeException e2) {
					log.debug( "Encountered failure rolling back failed commit", e2 );;
				}
				throw e;
			}
		}
