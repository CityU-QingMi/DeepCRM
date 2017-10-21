	@Test
	public void testTimeGeneratedByColumnDefinition() {
		final Entity eOrig = new Entity();

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( eOrig );
		s.getTransaction().commit();
		s.close();

		assertNotNull( eOrig.tsColumnDefinition );

		s = openSession();
		s.getTransaction().begin();
		final Entity eGotten = (Entity) s.get( Entity.class, eOrig.id );
		final String tsColumnDefinitionOrigFormatted = timestampFormat.format( eOrig.tsColumnDefinition );
		final String tsColumnDefinitionGottenFormatted = timestampFormat.format( eGotten.tsColumnDefinition );
		assertEquals( tsColumnDefinitionOrigFormatted , tsColumnDefinitionGottenFormatted );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithParameter =
				s.createQuery( "from MySQL57TimestampPropertyTest$Entity where tsColumnDefinition=?" )
						.setParameter( 0, eOrig.tsColumnDefinition );
		final Entity eQueriedWithParameter = (Entity) queryWithParameter.uniqueResult();
		assertNotNull( eQueriedWithParameter );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		final Query queryWithTimestamp =
				s.createQuery( "from MySQL57TimestampPropertyTest$Entity where tsColumnDefinition=?" )
						.setTimestamp( 0, eOrig.tsColumnDefinition );
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
