	@Test
	public void testIncompleteScrollFirstResultInTransaction() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc" ).scroll();
		results.next();
		Parent p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		tx.commit();
		s.close();
	}
