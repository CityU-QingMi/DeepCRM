	@Override
	public R getSingleResult() {
		try {
			final List<R> list = list();
			if ( list.size() == 0 ) {
				throw new NoResultException( "No entity found for query" );
			}
			return uniqueElement( list );
		}
		catch ( HibernateException e ) {
			if ( getProducer().getFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
				throw getExceptionConverter().convert( e );
			}
			else {
				throw e;
			}
		}
	}
