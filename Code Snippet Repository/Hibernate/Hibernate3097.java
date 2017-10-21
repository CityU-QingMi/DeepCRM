	private void fireRefresh(RefreshEvent event) {
		try {
			if ( !getSessionFactory().getSessionFactoryOptions().isAllowRefreshDetachedEntity() ) {
				if ( event.getEntityName() != null ) {
					if ( !contains( event.getEntityName(), event.getObject() ) ) {
						throw new IllegalArgumentException( "Entity not managed" );
					}
				}
				else {
					if ( !contains( event.getObject() ) ) {
						throw new IllegalArgumentException( "Entity not managed" );
					}
				}
			}
			checkTransactionSynchStatus();
			for ( RefreshEventListener listener : listeners( EventType.REFRESH ) ) {
				listener.onRefresh( event );
			}
		}
		catch (RuntimeException e) {
			if ( !getSessionFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
				if ( e instanceof HibernateException ) {
					throw e;
				}
			}
			//including HibernateException
			throw exceptionConverter.convert( e );
		}
		finally {
			delayedAfterCompletion();
		}
	}
