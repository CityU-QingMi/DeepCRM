	@Test
	@TestForIssue( jiraKey = "" )
	public void testComparableTimestamps() {
		final VersionType versionType =
				sessionFactory().getEntityPersister( Thing.class.getName() ).getVersionType();
		assertSame( RowVersionType.INSTANCE, versionType );

		Session s = openSession();
		s.getTransaction().begin();
		Thing thing = new Thing();
		thing.name = "n";
		s.persist( thing );
		s.getTransaction().commit();
		s.close();

		byte[] previousVersion = thing.version;
		for ( int i = 0 ; i < 20 ; i++ ) {
			try {
				Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			s = openSession();
			s.getTransaction().begin();
			thing.name = "n" + i;
			thing = (Thing) s.merge( thing );
			s.getTransaction().commit();
			s.close();

			assertTrue( versionType.compare( previousVersion, thing.version ) < 0 );
			previousVersion = thing.version;
		}

		s = openSession();
		s.getTransaction().begin();
		s.delete( thing );
		s.getTransaction().commit();
		s.close();
	}
