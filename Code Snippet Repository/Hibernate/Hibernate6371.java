	@Test
	public void testQueryInAndComposite() {

		Session s = openSession(  );
		Transaction transaction = s.beginTransaction();
		createData( s );
        s.flush();
        List ids = new ArrayList<SomeEntityId>(2);
        ids.add( new SomeEntityId(1,12) );
        ids.add( new SomeEntityId(10,23) );

        Criteria criteria = s.createCriteria( SomeEntity.class );
        Disjunction disjunction = Restrictions.disjunction();

        disjunction.add( Restrictions.in( "id", ids  ) );
        criteria.add( disjunction );

        List list = criteria.list();
        assertEquals( 2, list.size() );
		transaction.rollback();
		s.close();
	}
