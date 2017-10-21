	public void testStartOfSequence() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		final Person person = new Person();
		s.persist( person );
		tx.commit();
		s.close();

		assertTrue( person.getId() > 0 );
		assertTrue( sqlStatementInterceptor.getSqlQueries()
							.stream()
							.filter( sql -> sql.contains( "product_sequence" ) )
							.findFirst()
							.isPresent() );
	}
