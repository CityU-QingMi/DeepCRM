	protected void afterQuery() {
		if ( sessionFlushMode != null ) {
			getProducer().setHibernateFlushMode( sessionFlushMode );
			sessionFlushMode = null;
		}
		if ( sessionCacheMode != null ) {
			getProducer().setCacheMode( sessionCacheMode );
			sessionCacheMode = null;
		}
	}
