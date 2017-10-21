	public static void doInJPA(
			Supplier<EntityManagerFactory> factorySupplier,
			JPATransactionVoidFunction function,
			Map properties) {
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = properties == null ?
				factorySupplier.get().createEntityManager():
				factorySupplier.get().createEntityManager(properties);
			function.beforeTransactionCompletion();
			txn = entityManager.getTransaction();
			txn.begin();
			function.accept( entityManager );
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
			if ( entityManager != null ) {
				entityManager.close();
			}
		}
	}
