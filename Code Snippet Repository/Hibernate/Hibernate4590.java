		@Override
		public void rollback() {
			if ( rollbackOnly || getStatus() == TransactionStatus.ACTIVE ) {
				rollbackOnly = false;
				jdbcResourceTransaction.rollback();
				JdbcResourceLocalTransactionCoordinatorImpl.this.afterCompletionCallback( false );
			}

			// no-op otherwise.
		}
