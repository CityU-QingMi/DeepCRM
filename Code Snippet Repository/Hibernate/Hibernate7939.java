	@Test
	public void testOrderedWithCustomColumnReadAndWrite() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		SimpleEntityWithAssociation first = new SimpleEntityWithAssociation();
		first.setNegatedNumber( 1 );
		s.save( first );
		SimpleEntityWithAssociation second = new SimpleEntityWithAssociation();
		second.setNegatedNumber(2);
		s.save( second );
		s.flush();

		// Check order via SQL. Numbers are negated in the DB, so second comes first.
		List listViaSql = s.createSQLQuery("select ID from SIMPLE_1 order by negated_num").list();
		assertEquals( 2, listViaSql.size() );
		assertEquals( second.getId().longValue(), ((Number) listViaSql.get( 0 )).longValue() );
		assertEquals( first.getId().longValue(), ((Number) listViaSql.get( 1 )).longValue() );

		// Check order via HQL. Now first comes first b/c the read negates the DB negation.
		List listViaHql = s.createQuery("from SimpleEntityWithAssociation order by negatedNumber").list();
		assertEquals( 2, listViaHql.size() );
		assertEquals(first.getId(), ((SimpleEntityWithAssociation)listViaHql.get(0)).getId());
		assertEquals(second.getId(), ((SimpleEntityWithAssociation)listViaHql.get(1)).getId());

		s.delete( first );
		s.delete( second );
		t.commit();
		s.close();
	}
