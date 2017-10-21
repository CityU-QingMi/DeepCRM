	protected void closeEntityManagerAfterFailedBegin(JpaTransactionObject txObject) {
		if (txObject.isNewEntityManagerHolder()) {
			EntityManager em = txObject.getEntityManagerHolder().getEntityManager();
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			}
			catch (Throwable ex) {
				logger.debug("Could not rollback EntityManager after failed transaction begin", ex);
			}
			finally {
				EntityManagerFactoryUtils.closeEntityManager(em);
			}
			txObject.setEntityManagerHolder(null, false);
		}
	}
