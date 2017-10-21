	private void testWithSpecifiedLockMode(LockModeType lockModeType) {
		// makes sure we have an entity to actually query
		final Object id = doInHibernate( this::sessionFactory, session -> {
			return session.save( new SomeEntity() );
		} );

		// tests that both the query execution doesn't throw a SQL syntax (which is the main bug) and that
		// the query returns an expected entity object.
		doInHibernate( this::sessionFactory, session -> {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
			SomeEntity entity = session
					.createQuery( "SELECT e FROM SomeEntity e WHERE e.id = :id", SomeEntity.class )
					.setParameter( "id", id )
					.setLockMode( lockModeType )
					.uniqueResult();

			assertNotNull( entity );
		} );
	}
