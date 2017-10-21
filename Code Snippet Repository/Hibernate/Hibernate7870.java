	@Test
	@SuppressWarnings( {""})
	public void testPersistOutsideTransactionCascadedToInverseCollection() {
		long initialInsertCount = sessionFactory().getStatistics().getEntityInsertCount();
		Session s = openSession();
		try {
			MyEntity myEntity2 = new MyEntity( "test-persist-2" );
			MyChild child = new MyChild( "test-child-persist-inverse" );
			myEntity2.getInverseChildren().add( child );
			child.setInverseParent( myEntity2 );
			s.persist( myEntity2 );
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
