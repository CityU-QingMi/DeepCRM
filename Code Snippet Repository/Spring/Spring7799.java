		@Override
		protected void flushResource(EntityManagerHolder resourceHolder) {
			EntityManager em = resourceHolder.getEntityManager();
			if (em instanceof EntityManagerProxy) {
				EntityManager target = ((EntityManagerProxy) em).getTargetEntityManager();
				if (TransactionSynchronizationManager.hasResource(target)) {
					// ExtendedEntityManagerSynchronization active after joinTransaction() call:
					// flush synchronization already registered.
					return;
				}
			}
			try {
				em.flush();
			}
			catch (RuntimeException ex) {
				DataAccessException dae;
				if (this.jpaDialect != null) {
					dae = this.jpaDialect.translateExceptionIfPossible(ex);
				}
				else {
					dae = convertJpaAccessExceptionIfPossible(ex);
				}
				throw (dae != null ? dae : ex);
			}
		}
