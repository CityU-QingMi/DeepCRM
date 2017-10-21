	@Test
	public void testCustomColumnReadAndWrite() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Child c = new Child( "Child One" );
		c.setPosition( 1 );
		Parent p = new Parent( "Parent" );
		p.getChildren().add( c );
		c.setParent( p );
		s.save( p );
		s.flush();

		// Oracle returns BigDecimaal while other dialects return Integer;
		// casting to Number so it works on all dialects
		Number sqlValue = ((Number) s.createSQLQuery("select child_position from ParentChild c where c.name='Child One'")
				.uniqueResult());
		assertEquals( 0, sqlValue.intValue() );

		Integer hqlValue = (Integer)s.createQuery("select c.position from Parent p join p.children c where p.name='Parent'")
				.uniqueResult();
		assertEquals( 1, hqlValue.intValue() );

		p = (Parent)s.createCriteria(Parent.class).add(Restrictions.eq("name", "Parent")).uniqueResult();
		c = (Child)p.getChildren().iterator().next();
		assertEquals( 1, c.getPosition() );

		p = (Parent)s.createQuery("from Parent p join p.children c where c.position = 1").uniqueResult();
		c = (Child)p.getChildren().iterator().next();
		assertEquals( 1, c.getPosition() );

		c.setPosition( 2 );
		s.flush();
		sqlValue = ( (Number) s.createSQLQuery("select child_position from ParentChild c where c.name='Child One'")
				.uniqueResult() );
		assertEquals( 1, sqlValue.intValue() );
		s.delete( p );
		t.commit();
		s.close();
	}
