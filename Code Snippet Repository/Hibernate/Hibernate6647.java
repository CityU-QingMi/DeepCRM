	@Test
	public void testIdClassWithMappedSuperclassAndId() throws Exception {
		Session session = openSession();
		try {
			// Persist the entity
			Simple simple = new Simple();
			simple.setSimpleId( "1" );
			simple.setCategoryId( "2" );
			session.getTransaction().begin();
			session.save( simple );
			session.getTransaction().commit();
			session.clear();

			// Query the entity.
			session.getTransaction().begin();
			simple = session.createQuery( "FROM Simple", Simple.class ).getSingleResult();
			session.getTransaction().commit();

			// tests.
			assertNotNull( simple );
			assertEquals( "1", simple.getSimpleId() );
			assertEquals( "2", simple.getCategoryId() );

		}
		catch ( Throwable t ) {
			if ( session.getTransaction().isActive() ) {
				session.getTransaction().rollback();
			}
			throw t;
		}
		finally {
			session.close();
		}
	}
