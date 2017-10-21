	@Test
	public void testExample() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Master m = new Master();
		m.setName("name");
		m.setX(5);
		m.setOtherMaster(m);
		s.save(m);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Master m1 = (Master) s.createCriteria(Master.class)
			.add( Example.create(m).enableLike().ignoreCase().excludeProperty("bigDecimal") )
			.uniqueResult();
		assertTrue( m1.getOtherMaster()==m1 );
		m1 = (Master) s.createCriteria(Master.class)
			.add( Restrictions.eq("name", "foobar") )
			.uniqueResult();
		assertTrue( m1==null );
		m1 = (Master) s.createCriteria(Master.class)
			.add( Example.create(m).excludeProperty("bigDecimal") )
			.createCriteria("otherMaster")
				.add( Example.create(m).excludeZeroes().excludeProperty("bigDecimal") )
			.uniqueResult();
		assertTrue( m1.getOtherMaster()==m1 );
		Master m2 = (Master) s.createCriteria(Master.class)
			.add( Example.create(m).excludeNone().excludeProperty("bigDecimal") )
			.uniqueResult();
		assertTrue( m2==m1 );
		m.setName(null);
		m2 = (Master) s.createCriteria(Master.class)
			.add( Example.create(m).excludeNone().excludeProperty("bigDecimal") )
			.uniqueResult();
		assertTrue( null == m2 );
		if (getDialect() instanceof HSQLDialect || getDialect() instanceof MySQLDialect) {
			m1.setOtherMaster(null);
			s.flush();
		}
		s.delete(m1);
		t.commit();
		s.close();
	}
