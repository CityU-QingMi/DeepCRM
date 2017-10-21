	private SharedSessionContractImplementor openTemporarySessionForLoading() {
		if ( sessionFactoryUuid == null ) {
			throwLazyInitializationException( "SessionFactory UUID not known to create temporary Session for loading" );
		}

		final SessionFactoryImplementor sf = (SessionFactoryImplementor)
				SessionFactoryRegistry.INSTANCE.getSessionFactory( sessionFactoryUuid );
		final SharedSessionContractImplementor session = (SharedSessionContractImplementor) sf.openSession();
		session.getPersistenceContext().setDefaultReadOnly( true );
		session.setFlushMode( FlushMode.MANUAL );
		return session;
	}
