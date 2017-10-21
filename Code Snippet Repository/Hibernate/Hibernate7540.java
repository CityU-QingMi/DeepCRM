	@Test
	public void testIn() {
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		session.save( new Woman() );
		session.save( new Man() );
		session.flush();
		tx.commit();
		session.close();
		session = openSession();
		tx = session.beginTransaction();
		List persons = session.createCriteria( Person.class ).add(
				Restrictions.in( "class", Woman.class ) ).list();
		assertEquals( 1, persons.size() );
		tx.rollback();
		session.close();
	}
