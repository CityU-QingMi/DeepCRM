	@Test
	@SuppressWarnings( {""})
	public void testPersistOutsideTransactionCascadedToNonInverseCollection() {
		long initialInsertCount = sessionFactory().getStatistics().getEntityInsertCount();
		Session s = openSession();
		try {
			MyEntity myEntity = new MyEntity( "test-persist" );
			myEntity.getNonInverseChildren().add( new MyChild( "test-child-persist-non-inverse" ) );
			s.persist( myEntity );
			assertEquals( "persist on identity column not delayed", initialInsertCount, sessionFactory().getStatistics().getEntityInsertCount() );
			assertNull( myEntity.getId() );
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
