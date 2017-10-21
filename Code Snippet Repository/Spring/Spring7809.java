		@Override
		public void afterCompletion(int status) {
			try {
				super.afterCompletion(status);
				if (status != STATUS_COMMITTED) {
					// Haven't had an afterCommit call: trigger a rollback.
					try {
						this.entityManager.getTransaction().rollback();
					}
					catch (RuntimeException ex) {
						throw convertException(ex);
					}
				}
			}
			finally {
				if (this.closeOnCompletion) {
					EntityManagerFactoryUtils.closeEntityManager(this.entityManager);
				}
			}
		}
