	@Test
	public void testBidirForeignIdGenerator() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		OwnerAddress address = new OwnerAddress();
		address.setOwner( null );
		try {
			s.persist( address );
			s.flush();
			fail( "should have failed with IdentifierGenerationException" );
		}
		catch (PersistenceException ex) {
			assertTyping(IdentifierGenerationException.class, ex.getCause());
			// expected
		}
		finally {
			tx.rollback();
		}
		s.close();
	}
