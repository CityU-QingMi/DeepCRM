	@Test
	public void testFiltersOnSimpleHqlDelete() {
		Session session = openSession();
		session.beginTransaction();
		Salesperson sp = new Salesperson();
		sp.setName( "steve" );
		sp.setRegion( "NA" );
		session.persist( sp );
		Salesperson sp2 = new Salesperson();
		sp2.setName( "john" );
		sp2.setRegion( "APAC" );
		session.persist( sp2 );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.enableFilter( "region" ).setParameter( "region", "NA" );
		int count = session.createQuery( "delete from Salesperson" ).executeUpdate();
		assertEquals( 1, count );
		session.delete( sp2 );
		session.getTransaction().commit();
		session.close();
	}
