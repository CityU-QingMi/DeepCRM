	public static <T> T doInJPA(
			Supplier<EntityManagerFactory> factorySupplier,
			JPATransactionFunction<T> function,
			Map properties) {
		T result = null;
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = properties == null ?
				factorySupplier.get().createEntityManager():
				factorySupplier.get().createEntityManager(properties);
			function.beforeTransactionCompletion();
			txn = entityManager.getTransaction();
			txn.begin();
			result = function.apply( entityManager );
			txn.commit();
		}
		catch ( Throwable e ) {
			if ( txn != null && txn.isActive() ) {
				txn.rollback();
			}
			throw e;
		}
		finally {
			function.afterTransactionCompletion();
			if ( entityManager != null ) {
				entityManager.close();
			}
		}
		return result;
	}
