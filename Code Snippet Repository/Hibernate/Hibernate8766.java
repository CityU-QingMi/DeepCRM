	@Override
	protected void cleanupTest() {
		if ( sessionFactory() != null ) {
			Session s = openSession();
			s.beginTransaction();
			s.createQuery( "delete from " + membership.getClass().getName() );
			s.createQuery( "delete from User" );
			s.createQuery( "delete from Group" );
			s.getTransaction().commit();
			s.close();
		}
	}
