	@Test
	@TestForIssue( jiraKey = "" )
	public void testGetAndFindNonEntityThrowsIllegalArgumentException() {
		try {
			sessionFactory().locateEntityPersister( Cellular.class );
		}
		catch (UnknownEntityTypeException ignore) {
			// expected
		}

		try {
			sessionFactory().locateEntityPersister( Cellular.class.getName() );
		}
		catch (UnknownEntityTypeException ignore) {
			// expected
		}

		Session s = openSession();
		s.beginTransaction();
		try {
			s.get( Cellular.class, 1 );
			fail( "Expecting a failure" );
		}
		catch (UnknownEntityTypeException ignore) {
			// expected
		}
		finally {
			s.getTransaction().commit();
			s.close();
		}

		s = openSession();
		s.beginTransaction();
		try {
			s.get( Cellular.class.getName(), 1 );
			fail( "Expecting a failure" );
		}
		catch (UnknownEntityTypeException ignore) {
			// expected
		}
		finally {
			s.getTransaction().commit();
			s.close();
		}
	}
