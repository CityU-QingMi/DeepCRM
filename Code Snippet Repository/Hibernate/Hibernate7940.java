	@Test
	public void testHavingWithCustomColumnReadAndWrite() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		SimpleEntityWithAssociation first = new SimpleEntityWithAssociation();
		first.setNegatedNumber(5);
		first.setName( "simple" );
		s.save(first);
		SimpleEntityWithAssociation second = new SimpleEntityWithAssociation();
		second.setNegatedNumber( 10 );
		second.setName("simple");
		s.save(second);
		SimpleEntityWithAssociation third = new SimpleEntityWithAssociation();
		third.setNegatedNumber( 20 );
		third.setName( "complex" );
		s.save( third );
		s.flush();

		// Check order via HQL. Now first comes first b/c the read negates the DB negation.
		Number r = (Number)s.createQuery("select sum(negatedNumber) from SimpleEntityWithAssociation " +
				"group by name having sum(negatedNumber) < 20").uniqueResult();
		assertEquals(r.intValue(), 15);

		s.delete(first);
		s.delete(second);
		s.delete(third);
		t.commit();
		s.close();

	}
