	private boolean shouldFlush() {
		if ( getProducer().isTransactionInProgress() ) {
			FlushMode effectiveFlushMode = getHibernateFlushMode();
			if ( effectiveFlushMode == null ) {
				effectiveFlushMode = getProducer().getHibernateFlushMode();
			}

			if ( effectiveFlushMode == FlushMode.ALWAYS ) {
				return true;
			}

			if ( effectiveFlushMode == FlushMode.AUTO ) {
				if ( getProducer().getFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
					return true;
				}
			}
		}

		return false;
	}
