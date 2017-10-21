	@Test
	public void testUpdateNonExistentEntity() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		try {
			s.createQuery( "update NonExistentEntity e set e.someProp = ?" ).executeUpdate();
			fail( "no exception thrown" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
		}

		t.commit();
		s.close();
	}
