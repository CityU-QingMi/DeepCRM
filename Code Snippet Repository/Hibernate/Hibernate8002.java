	@Test
	public void testDeleteNonExistentEntity() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		try {
			s.createQuery( "delete NonExistentEntity" ).executeUpdate();
			fail( "no exception thrown" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException ignore ) {
		}

		t.commit();
		s.close();
	}
