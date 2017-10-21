	private SharedSessionContractImplementor openTemporarySessionForLoading(LazyInitializationWork lazyInitializationWork) {
		if ( consumer.getSessionFactoryUuid() == null ) {
			throwLazyInitializationException( Cause.NO_SF_UUID, lazyInitializationWork );
		}

		final SessionFactoryImplementor sf = (SessionFactoryImplementor)
				SessionFactoryRegistry.INSTANCE.getSessionFactory( consumer.getSessionFactoryUuid() );
		final SharedSessionContractImplementor session = (SharedSessionContractImplementor) sf.openSession();
		session.getPersistenceContext().setDefaultReadOnly( true );
		session.setHibernateFlushMode( FlushMode.MANUAL );
		return session;
	}
