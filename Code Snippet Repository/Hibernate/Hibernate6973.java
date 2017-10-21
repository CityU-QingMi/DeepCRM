	@Test
	public void testUnionSubClass() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Machine computer = new Machine();
		computer.setWeight( new Double( 4 ) );
		Robot asimov = new Robot();
		asimov.setWeight( new Double( 120 ) );
		asimov.setName( "Asimov" );
		T800 terminator = new T800();
		terminator.setName( "Terminator" );
		terminator.setWeight( new Double( 300 ) );
		terminator.setTargetName( "Sarah Connor" );
		s.persist( computer );
		s.persist( asimov );
		s.persist( terminator );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "from Machine m where m.weight >= :weight" );
		q.setDouble( "weight", new Double( 10 ) );
		List result = q.list();
		assertEquals( 2, result.size() );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		tx.commit();
		s.close();
	}
