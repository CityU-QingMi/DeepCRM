	@Test
	public void testPersistOutsideTransactionCascadedFromManyToOne() {
		long initialInsertCount = sessionFactory().getStatistics().getEntityInsertCount();
		Session s = openSession();
		try {
			MyEntity myEntity2 = new MyEntity( "test-persist-2" );
			MySibling sibling = new MySibling( "test-persist-sibling-in" );
			sibling.setEntity( myEntity2 );
			s.persist( sibling );
			assertEquals( "persist on identity column not delayed", initialInsertCount, sessionFactory().getStatistics().getEntityInsertCount() );
			assertNull( myEntity2.getId() );
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
