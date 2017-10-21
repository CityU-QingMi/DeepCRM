	@Override
	public RuntimeException convertCommitException(RuntimeException e) {
		if ( sharedSessionContract.getFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
			Throwable wrappedException;
			if ( e instanceof HibernateException ) {
				wrappedException = convert( (HibernateException) e );
			}
			else if ( e instanceof PersistenceException ) {
				Throwable cause = e.getCause() == null ? e : e.getCause();
				if ( cause instanceof HibernateException ) {
					wrappedException = convert( (HibernateException) cause );
				}
				else {
					wrappedException = cause;
				}
			}
			else {
				wrappedException = e;
			}
			try {
				//as per the spec we should rollback if commit fails
				sharedSessionContract.getTransaction().rollback();
			}
			catch (Exception re) {
				//swallow
			}
			return new RollbackException( "Error while committing the transaction", wrappedException );
		}
		else {
			return e;
		}
	}
