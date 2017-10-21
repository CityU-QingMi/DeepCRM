	@Test
	public void testTime() {
		final Entity eOrig = new Entity();
		eOrig.ts = new Date();

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( eOrig );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Entity eGotten = (Entity) s.get( Entity.class, eOrig.id );
		final String tsOrigFormatted = timestampFormat.format( eOrig.ts );
		final String tsGottenFormatted = timestampFormat.format( eGotten.ts );
		assertEquals( tsOrigFormatted , tsGottenFormatted );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithParameter = s.createQuery( "from MySQL57TimestampPropertyTest$Entity where ts=?" ).setParameter( 0, eOrig.ts );
		final Entity eQueriedWithParameter = (Entity) queryWithParameter.uniqueResult();
		assertNotNull( eQueriedWithParameter );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithTimestamp = s.createQuery( "from MySQL57TimestampPropertyTest$Entity where ts=?" ).setTimestamp( 0, eOrig.ts );
		final Entity eQueriedWithTimestamp = (Entity) queryWithTimestamp.uniqueResult();
		assertNotNull( eQueriedWithTimestamp );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		s.delete( eQueriedWithTimestamp );
		s.getTransaction().commit();
		s.close();
	}
