	protected void before() {
		if ( flushMode != null ) {
			sessionFlushMode = getSession().getHibernateFlushMode();
			getSession().setHibernateFlushMode( flushMode );
		}
		if ( cacheMode != null ) {
			sessionCacheMode = getSession().getCacheMode();
			getSession().setCacheMode( cacheMode );
		}
	}
