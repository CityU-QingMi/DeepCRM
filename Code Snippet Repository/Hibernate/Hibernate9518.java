	@Test
	public void testTimeGeneratedByColumnDefault() {
		final Entity eOrig = new Entity();

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( eOrig );
		s.getTransaction().commit();
		s.close();

		assertNotNull( eOrig.tsColumnDefault );

		s = openSession();
		s.getTransaction().begin();
		final Entity eGotten = (Entity) s.get( Entity.class, eOrig.id );
		final String tsColumnDefaultOrigFormatted = timestampFormat.format( eOrig.tsColumnDefault );
		final String tsColumnDefaultGottenFormatted = timestampFormat.format( eGotten.tsColumnDefault );
		assertEquals( tsColumnDefaultOrigFormatted , tsColumnDefaultGottenFormatted );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithParameter =
				s.createQuery( "from MySQL57TimestampPropertyTest$Entity where tsColumnDefault=?" )
						.setParameter( 0, eOrig.tsColumnDefault );
		final Entity eQueriedWithParameter = (Entity) queryWithParameter.uniqueResult();
		assertNotNull( eQueriedWithParameter );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithTimestamp =
				s.createQuery( "from MySQL57TimestampPropertyTest$Entity where tsColumnDefault=?" )
						.setTimestamp( 0, eOrig.tsColumnDefault );
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
