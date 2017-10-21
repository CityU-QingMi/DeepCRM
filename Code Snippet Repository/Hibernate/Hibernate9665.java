	@Test
	@TestForIssue( jiraKey = "" )
	public void testComparableTimestamps() {
		final VersionType versionType =
				sessionFactory().getEntityPersister( User.class.getName() ).getVersionType();
		assertSame( RowVersionType.INSTANCE, versionType );

		Session s = openSession();
		s.getTransaction().begin();
		User user = new User();
		user.setUsername( "n" );
		s.persist( user );
		s.getTransaction().commit();
		s.close();

		byte[] previousTimestamp = user.getTimestamp();
		for ( int i = 0 ; i < 20 ; i++ ) {
			try {
				Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			s = openSession();
			s.getTransaction().begin();
			user.setUsername( "n" + i );
			user = (User) s.merge( user );
			s.getTransaction().commit();
			s.close();

			assertTrue( versionType.compare( previousTimestamp, user.getTimestamp() ) < 0 );
			previousTimestamp = user.getTimestamp();
		}

		s = openSession();
		s.getTransaction().begin();
		s.delete( user );
		s.getTransaction().commit();
		s.close();
	}
