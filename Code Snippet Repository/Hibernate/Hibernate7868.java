	@Test
	public void testPersistOutsideTransaction() {
		Session s = openSession();
		try {
			// first test save() which should force an immediate insert...
			MyEntity myEntity1 = new MyEntity( "test-save" );
			Long id = (Long) s.save( myEntity1 );
			assertNotNull( "identity column did not force immediate insert", id );
			assertEquals( id, myEntity1.getId() );

			// next test persist() which should cause a delayed insert...
			long initialInsertCount = sessionFactory().getStatistics().getEntityInsertCount();
			MyEntity myEntity2 = new MyEntity( "test-persist" );
			s.persist( myEntity2 );
			assertEquals( "persist on identity column not delayed", initialInsertCount, sessionFactory().getStatistics().getEntityInsertCount() );
			assertNull( myEntity2.getId() );

			// an explicit flush should cause execution of the delayed insertion
			s.flush();
			fail( "TransactionRequiredException required upon flush" );
		}
		catch ( PersistenceException ex ) {
			// expected
			assertTyping( TransactionRequiredException.class, ex );
		}
		finally {
			s.close();
		}
	}
