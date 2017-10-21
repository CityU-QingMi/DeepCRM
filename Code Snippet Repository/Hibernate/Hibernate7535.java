	@Test
	public void testDetachedCriteria() {
		DetachedCriteria dc = DetachedCriteria.forClass(Student.class)
			.add( Property.forName("name").eq("Gavin King") )
			.addOrder( Order.asc("studentNumber") )
			.setProjection( Property.forName("studentNumber") );

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

		List result = dc.getExecutableCriteria(session)
			.setMaxResults(3)
			.list();

		assertEquals( result.size(), 2 );
		assertEquals( result.get(0), new Long(232) );
		assertEquals( result.get(1), new Long(666) );

		session.delete(gavin);
		session.delete(bizarroGavin);
		t.commit();
		session.close();
	}
