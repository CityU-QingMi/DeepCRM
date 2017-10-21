	@Test
	public void testMergeStaleVersionFails() throws Exception {
		Session s = openSession();
        s.beginTransaction();
		VersionedEntity entity = new VersionedEntity( "entity", "entity" );
		s.persist( entity );
		s.getTransaction().commit();
		s.close();

		// make the detached 'entity' reference stale...
		s = openSession();
        s.beginTransaction();
		VersionedEntity entity2 = ( VersionedEntity ) s.get( VersionedEntity.class, entity.getId() );
		entity2.setName( "entity-name" );
		s.getTransaction().commit();
		s.close();

		// now try to reattch it
		s = openSession();
		s.beginTransaction();
		try {
			s.merge( entity );
			s.getTransaction().commit();
			fail( "was expecting staleness error" );
		}
		catch (PersistenceException e){
			// expected
			assertTyping( StaleObjectStateException.class, e.getCause());
		}
		finally {
			s.getTransaction().rollback();
			s.close();
		}
	}
