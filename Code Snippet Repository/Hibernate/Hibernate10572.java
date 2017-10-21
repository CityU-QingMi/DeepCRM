	private static Session getSession(EntityManager em) {
		Object delegate = em.getDelegate();
		if ( delegate instanceof Session ) {
			return (Session) delegate;
		}
		else if ( delegate instanceof EntityManager ) {
			Object delegate2 = ((EntityManager) delegate).getDelegate();

			if ( delegate2 instanceof Session ) {
				return (Session) delegate2;
			}
		}

		throw new RuntimeException( "Invalid entity manager" );
	}
