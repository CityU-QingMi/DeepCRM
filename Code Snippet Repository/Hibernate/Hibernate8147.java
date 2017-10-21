	@Test
	public void testTupleReturnFails() {
		Session s = openSession();
		Transaction txn = s.beginTransaction();

		try {
			s.createQuery( "select a, a.weight from Animal a inner join fetch a.offspring" ).scroll();
			fail( "scroll allowed with collection fetch and reurning tuples" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( HibernateException e ) {
			// expected result...
		}

		txn.commit();
		s.close();
	}
