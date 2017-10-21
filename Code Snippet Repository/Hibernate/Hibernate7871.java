	@Test
	public void testPersistOutsideTransactionCascadedToManyToOne() {
		long initialInsertCount = sessionFactory().getStatistics().getEntityInsertCount();
		Session s = openSession();
		try {
			MyEntity myEntity = new MyEntity( "test-persist" );
			myEntity.setSibling( new MySibling( "test-persist-sibling-out" ) );
			s.persist( myEntity );
			assertEquals( "persist on identity column not delayed", initialInsertCount, sessionFactory().getStatistics().getEntityInsertCount() );
			assertNull( myEntity.getId() );
			s.flush();
			fail( "TransactionRequiredException expected upon flush." );
		}
		catch ( PersistenceException ex ) {
			// expected
			assertTyping( TransactionRequiredException.class, ex );
		}
		finally {
			s.close();
		}
	}
