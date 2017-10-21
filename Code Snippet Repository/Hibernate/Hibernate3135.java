	@Override
	public void close() throws HibernateException {
		log.tracef( "Closing session [%s]", getSessionIdentifier() );

		// todo : we want this check if usage is JPA, but not native Hibernate usage
		if ( getSessionFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
			// Original hibernate-entitymanager EM#close behavior
			checkSessionFactoryOpen();
			checkOpenOrWaitingForAutoClose();
			if ( discardOnClose || !isTransactionInProgress( false ) ) {
				super.close();
			}
			else {
				//Otherwise, session auto-close will be enabled by shouldAutoCloseSession().
				waitingForAutoClose = true;
				closed = true;
			}
		}
		else {
			super.close();
		}

		if ( getFactory().getStatistics().isStatisticsEnabled() ) {
			getFactory().getStatistics().closeSession();
		}
	}
