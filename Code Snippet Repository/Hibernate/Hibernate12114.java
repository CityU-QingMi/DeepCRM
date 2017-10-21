	public static void doInHibernateSessionBuilder(
			Supplier<SessionBuilder> sessionBuilderSupplier,
			HibernateTransactionConsumer function) {
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionBuilderSupplier.get().openSession();
			function.beforeTransactionCompletion();
			txn = session.beginTransaction();

			function.accept( session );
			if ( !txn.getRollbackOnly() ) {
				txn.commit();
			}
			else {
				try {
					txn.rollback();
				}
				catch (Exception e) {
					log.error( "Rollback failure", e );
				}
			}
		}
		catch ( Throwable t ) {
			if ( txn != null && txn.isActive() ) {
				try {
					txn.rollback();
				}
				catch (Exception e) {
					log.error( "Rollback failure", e );
				}
			}
			throw t;
		}
		finally {
			function.afterTransactionCompletion();
			if ( session != null ) {
				session.close();
			}
		}
	}
