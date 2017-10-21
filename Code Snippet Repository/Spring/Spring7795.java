	@Nullable
	private static Object prepareTransaction(EntityManager em, EntityManagerFactory emf) {
		if (emf instanceof EntityManagerFactoryInfo) {
			EntityManagerFactoryInfo emfInfo = (EntityManagerFactoryInfo) emf;
			JpaDialect jpaDialect = emfInfo.getJpaDialect();
			if (jpaDialect != null) {
				return jpaDialect.prepareTransaction(em,
						TransactionSynchronizationManager.isCurrentTransactionReadOnly(),
						TransactionSynchronizationManager.getCurrentTransactionName());
			}
		}
		return null;
	}
