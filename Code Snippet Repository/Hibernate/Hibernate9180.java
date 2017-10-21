	@Test
	public void testDetachedCriteria() {
		DetachedCriteria dc = DetachedCriteria.forClass(Student.class)
			.add( Property.forName("name").eq("Gavin King") )
			.addOrder( Order.asc("studentNumber") );

		byte[] bytes = SerializationHelper.serialize(dc);

		dc = (DetachedCriteria) SerializationHelper.deserialize( bytes );

		Session session = openSession();
		Transaction t = session.beginTransaction();

		Student gavin = new Student();
		gavin.setName("Gavin King");
		gavin.setStudentNumber(232);
		Student bizarroGavin = new Student();
		bizarroGavin.setName("Gavin King");
		bizarroGavin.setStudentNumber(666);
		session.persist(bizarroGavin);
		session.persist(gavin);

		t.commit();
		session.close();

		session = openSession();
		t = session.beginTransaction();

		List result = dc.getExecutableCriteria(session)
			.setMaxResults(3)
			.setReadOnly( true )
			.list();

		assertEquals( result.size(), 2 );
		gavin = ( Student ) result.get( 0 );
		bizarroGavin = ( Student ) result.get( 1 );
		assertEquals( 232, gavin.getStudentNumber() );
		assertEquals( 666, bizarroGavin.getStudentNumber() );
		assertTrue( session.isReadOnly( gavin ) );
		assertTrue( session.isReadOnly( bizarroGavin ) );

		session.delete(gavin);
		session.delete(bizarroGavin);
		t.commit();
		session.close();
	}
