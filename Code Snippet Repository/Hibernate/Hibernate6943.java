	@Test
	public void testSQLQuery() {
		Night n = new Night();
		Calendar c = new GregorianCalendar();
		c.set( 2000, 2, 2 );
		Date now = c.getTime();
		c.add( Calendar.MONTH, -1 );
		Date aMonthAgo = c.getTime();
		c.add( Calendar.MONTH, 2 );
		Date inAMonth = c.getTime();
		n.setDate( now );
		n.setDuration( 9999 );
		Area area = new Area();
		area.setName( "Monceau" );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( n );
		s.persist( area );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		Query q = s.getNamedQuery( "night.getAll.bySQL" );
		q.setParameter( 0, 9990 );
		List result = q.list();
		assertEquals( 1, result.size() );
		Night n2 = (Night) result.get( 0 );
		assertEquals( n2.getDuration(), n.getDuration() );
		List areas = s.getNamedQuery( "getAreaByNative" ).list();
		assertTrue( 1 == areas.size() );
		assertEquals( area.getName(), ( (Area) areas.get( 0 ) ).getName() );
		s.delete( areas.get( 0 ) );
		s.delete( n2 );
		tx.commit();
		s.close();
	}
