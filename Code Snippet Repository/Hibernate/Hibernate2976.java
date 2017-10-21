	@Override
	public Transaction getTransaction() throws HibernateException {
		if ( getFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
			// JPA requires that we throw IllegalStateException if this is called
			// on a JTA EntityManager
			if ( getTransactionCoordinator().getTransactionCoordinatorBuilder().isJta() ) {
				if ( !getFactory().getSessionFactoryOptions().isJtaTransactionAccessEnabled() ) {
					throw new IllegalStateException( "A JTA EntityManager cannot use getTransaction()" );
				}
			}
		}
		return accessTransaction();
	}
